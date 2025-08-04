package com.zsb.bluex.core.launch;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BluexInitializer implements ApplicationRunner {

    @Resource
    private MetaHolder metaHolder;
    @Resource
    private CustomPathConfig customPathConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 解析默认的流程控制节点
        metaHolder.processDefaultControl();
        // 解析默认的内置Java类型
        metaHolder.processDefaultType();
        // 扫描自定义的@BluexType
        metaHolder.processBluexType(customPathConfig.getTypeScanPaths());
        // 扫描自定义的@BluexFunctionLib
        metaHolder.processBluexFunction(customPathConfig.getFunctionScanPaths());

        System.out.println(MetaHolder.CONTROL_DEFINITION);
        System.out.println(MetaHolder.PRIMITIVE_TYPE_DEFINITION);
        System.out.println(MetaHolder.TYPE_DEFINITION);
        System.out.println(MetaHolder.FUNCTION_DEFINITION);
    }
}
