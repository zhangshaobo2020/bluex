package com.zsb.bluex.core.job.delegate;

import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.def.ParamDef;
import com.zsb.bluex.core.graph.GraphView;
import com.zsb.bluex.core.job.EventDelegate;
import com.zsb.bluex.core.launch.MetaHolder;
import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.delegate.DelegateNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Map;

@Slf4j
public class MQJob extends EventDelegate {

    private final String driver;          // 驱动类型: activemq / ibmmq / rabbitmq
    private final String brokerUrl;       // 地址，例如 tcp://127.0.0.1:61616
    private final String username;
    private final String password;
    private final String destinationName; // 队列/Topic 名称
    private final boolean pubSubDomain;   // true=Topic, false=Queue
    private final Map<String, Object> extraProps; // IBM MQ 特有参数等

    private DefaultMessageListenerContainer container;

    public MQJob(GraphView graphView,
                 String driver,
                 String brokerUrl,
                 String username,
                 String password,
                 String destinationName,
                 boolean pubSubDomain,
                 Map<String, Object> extraProps) {
        super(graphView);
        this.driver = driver;
        this.brokerUrl = brokerUrl;
        this.username = username;
        this.password = password;
        this.destinationName = destinationName;
        this.pubSubDomain = pubSubDomain;
        this.extraProps = extraProps;
    }

    @Override
    public void start() throws Exception {
        try {
            ConnectionFactory connectionFactory = createConnectionFactory(
                    driver, brokerUrl, username, password, extraProps
            );

            container = new DefaultMessageListenerContainer();
            container.setConnectionFactory(connectionFactory);
            container.setDestinationName(destinationName);
            container.setPubSubDomain(pubSubDomain);
            container.setMessageListener(new MQJobListener(this));
            container.afterPropertiesSet();
            container.start();

            log.info("启动 MQJob: driver={} url={} dest={} topic={} -> {}",
                    driver, brokerUrl, destinationName, pubSubDomain, this);
        } catch (Exception e) {
            log.error("MQJob 启动失败", e);
            throw e;
        }
    }

    @Override
    public void end() {
        try {
            if (container != null) {
                container.stop();
                container.destroy();
                container = null;
                log.info("停止 MQJob: {} -> {}", destinationName, this);
            }
        } catch (Exception e) {
            log.error("MQJob 停止失败", e);
        }
    }

    @Override
    public ControlDef provideDefinition() {
        ControlDef def = new ControlDef();
        def.setName("MQJob");
        def.setDisplayName("MQ消息监听");
        def.setCategory("事件委托|MQJob");
        def.setQualifiedName("DELEGATE:MQJob");
        def.setSignature("DELEGATE:MQJob");
        def.setDelegate(true);

        def.getOutputExecDefs().add(new ParamDef("Exec"));

        def.getOutputParamDefs().add(
                new ParamDef(
                        "Message",
                        MetaHolder.PRIMITIVE_DEFINITION.get("javax.jms.Message")
                )
        );
        return def;
    }

    /**
     * MQ 消息监听器
     */
    public static class MQJobListener implements SessionAwareMessageListener<Message> {
        private final MQJob job;

        public MQJobListener(MQJob job) {
            this.job = job;
        }

        @Override
        public void onMessage(Message message, Session session) {
            try {
                ExecutionContext newCtx = job.graphView.buildExecCtx();
                DelegateNode delegateNode = (DelegateNode) newCtx.findStartupNode();
                delegateNode.setOutput("Message", new OUTPUT<>(message));

                newCtx.run();
            } catch (Exception e) {
                log.error("MQJob 执行异常", e);
            }
        }
    }

    /**
     * 根据不同 MQ 动态创建 ConnectionFactory
     */
    private ConnectionFactory createConnectionFactory(String driver, String url,
                                                      String user, String pass,
                                                      Map<String, Object> extra) {
        try {
            switch (driver.toLowerCase()) {
                case "activemq": {
                    Class<?> clazz = Class.forName("org.apache.activemq.ActiveMQConnectionFactory");
                    return (ConnectionFactory) clazz
                            .getConstructor(String.class, String.class, String.class)
                            .newInstance(user, pass, url);
                }
                case "ibmmq": {
                    // IBM MQ
                    Class<?> clazz = Class.forName("com.ibm.mq.jms.MQConnectionFactory");
                    Object factory = clazz.newInstance();

                    if (extra != null) {
                        if (extra.containsKey("queueManager")) {
                            clazz.getMethod("setQueueManager", String.class)
                                    .invoke(factory, extra.get("queueManager"));
                        }
                        if (extra.containsKey("channel")) {
                            clazz.getMethod("setChannel", String.class)
                                    .invoke(factory, extra.get("channel"));
                        }
                        if (extra.containsKey("connectionNameList")) {
                            clazz.getMethod("setConnectionNameList", String.class)
                                    .invoke(factory, extra.get("connectionNameList")); // host(port)
                        }
                        if (extra.containsKey("transportType")) {
                            clazz.getMethod("setTransportType", int.class)
                                    .invoke(factory, extra.get("transportType"));
                        }
                    }
                    return (ConnectionFactory) factory;
                }
                case "rabbitmq": {
                    // RabbitMQ JMS Wrapper
                    Class<?> clazz = Class.forName("com.rabbitmq.jms.admin.RMQConnectionFactory");
                    Object factory = clazz.newInstance();
                    clazz.getMethod("setUri", String.class).invoke(factory, url);
                    clazz.getMethod("setUsername", String.class).invoke(factory, user);
                    clazz.getMethod("setPassword", String.class).invoke(factory, pass);
                    return (ConnectionFactory) factory;
                }
                default:
                    throw new IllegalArgumentException("不支持的 MQ 驱动: " + driver);
            }
        } catch (Exception e) {
            throw new RuntimeException("创建 ConnectionFactory 失败, driver=" + driver, e);
        }
    }
}
