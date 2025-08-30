package com.zsb.bluex.core.job.delegate;

import com.ibm.msg.client.wmq.WMQConstants;
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

@Slf4j
public class MQJob extends EventDelegate {

    private String mqDriverName;
    private String mqUri;
    private String mqUsername;
    private String mqPassword;
    private String destinationName;
    private String pubSubDomain;
    private String mqQueueManager;
    private String mqChannel;
    private String mqConnectionNameList;
    private String mqCcsId;

    private DefaultMessageListenerContainer container;

    public MQJob() {
    }

    public MQJob(GraphView graphView,
                 String mqDriverName,
                 String mqUri,
                 String mqUsername,
                 String mqPassword,
                 String destinationName,
                 String pubSubDomain,
                 String mqQueueManager,
                 String mqChannel,
                 String mqConnectionNameList,
                 String mqCcsId) {
        super(graphView);
        this.mqDriverName = mqDriverName;
        this.mqUri = mqUri;
        this.mqUsername = mqUsername;
        this.mqPassword = mqPassword;
        this.destinationName = destinationName;
        this.pubSubDomain = pubSubDomain;
        this.mqQueueManager = mqQueueManager;
        this.mqChannel = mqChannel;
        this.mqConnectionNameList = mqConnectionNameList;
        this.mqCcsId = mqCcsId;
    }

    @Override
    public void start() throws Exception {
        try {
            ConnectionFactory connectionFactory = createConnectionFactory(
                    mqDriverName,
                    mqUri,
                    mqUsername,
                    mqPassword,
                    mqQueueManager,
                    mqChannel,
                    mqConnectionNameList,
                    mqCcsId
            );

            container = new DefaultMessageListenerContainer();
            container.setConnectionFactory(connectionFactory);
            container.setDestinationName(destinationName);
            container.setPubSubDomain("Y".equals(pubSubDomain));
            container.setMessageListener(new MQJobListener(this));
            container.afterPropertiesSet();
            container.start();

            log.info("启动 MQJob: driver={} url={} dest={}", mqDriverName, mqUri, destinationName);
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
                        MetaHolder.PRIMITIVE_DEFINITION.get("java.lang.String")
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
                // 转为String
                String payload;
                if (message instanceof javax.jms.TextMessage) {
                    payload = ((javax.jms.TextMessage) message).getText();
                } else if (message instanceof javax.jms.BytesMessage) {
                    javax.jms.BytesMessage bytesMsg = (javax.jms.BytesMessage) message;
                    byte[] data = new byte[(int) bytesMsg.getBodyLength()];
                    bytesMsg.readBytes(data);
                    payload = new String(data, java.nio.charset.StandardCharsets.UTF_8);
                } else if (message instanceof javax.jms.ObjectMessage) {
                    Object obj = ((javax.jms.ObjectMessage) message).getObject();
                    payload = (obj != null ? obj.toString() : null);
                } else if (message instanceof javax.jms.MapMessage) {
                    javax.jms.MapMessage mapMsg = (javax.jms.MapMessage) message;
                    java.util.Enumeration<?> keys = mapMsg.getMapNames();
                    StringBuilder sb = new StringBuilder("{");
                    while (keys.hasMoreElements()) {
                        String key = keys.nextElement().toString();
                        Object val = mapMsg.getObject(key);
                        sb.append(key).append("=").append(val).append(", ");
                    }
                    if (sb.length() > 1) sb.setLength(sb.length() - 2);
                    sb.append("}");
                    payload = sb.toString();
                } else {
                    // 默认处理：直接调用 toString()
                    payload = message.toString();
                }

                ExecutionContext newCtx = job.graphView.buildExecCtx();
                DelegateNode delegateNode = (DelegateNode) newCtx.findStartupNode();
                delegateNode.setOutput("Message", new OUTPUT<>(payload));

                newCtx.run();
            } catch (Exception e) {
                log.error("MQJob 执行异常", e);
            }
        }
    }

    /**
     * 根据不同 MQ 动态创建 ConnectionFactory
     */
    private ConnectionFactory createConnectionFactory(String mqDriverName, String mqUri,
                                                      String mqUsername, String mqPassword,
                                                      String mqQueueManager, String mqChannel,
                                                      String mqConnectionNameList, String mqCcsId) {
        try {
            switch (mqDriverName.toLowerCase()) {
                case "activemq": {
                    Class<?> clazz = Class.forName("org.apache.activemq.ActiveMQConnectionFactory");
                    return (ConnectionFactory) clazz
                            .getConstructor(String.class, String.class, String.class)
                            .newInstance(mqUsername, mqPassword, mqUri);
                }
                case "ibmmq": {
                    // IBM MQ
                    Class<?> clazz = Class.forName("com.ibm.mq.jms.MQConnectionFactory");
                    Object factory = clazz.newInstance();

                    clazz.getMethod("setQueueManager", String.class).invoke(factory, mqQueueManager);
                    clazz.getMethod("setChannel", String.class).invoke(factory, mqChannel);
                    clazz.getMethod("setConnectionNameList", String.class).invoke(factory, mqConnectionNameList); // host(port)
                    clazz.getMethod("setCCSID", int.class).invoke(factory, Integer.valueOf(mqCcsId)); // 字符集
                    // 设置transportType
                    clazz.getMethod("setTransportType", int.class).invoke(factory, WMQConstants.WMQ_CM_CLIENT);
                    return (ConnectionFactory) factory;
                }
                case "rabbitmq": {
                    // RabbitMQ JMS Wrapper
                    Class<?> clazz = Class.forName("com.rabbitmq.jms.admin.RMQConnectionFactory");
                    Object factory = clazz.newInstance();
                    clazz.getMethod("setUri", String.class).invoke(factory, mqUri);
                    clazz.getMethod("setUsername", String.class).invoke(factory, mqUsername);
                    clazz.getMethod("setPassword", String.class).invoke(factory, mqPassword);
                    return (ConnectionFactory) factory;
                }
                default:
                    throw new IllegalArgumentException("不支持的 MQ 驱动: " + mqDriverName);
            }
        } catch (Exception e) {
            throw new RuntimeException("创建 ConnectionFactory 失败, driver=" + mqDriverName, e);
        }
    }
}
