package com.zsb.bluex.core.launch;

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
        // 解析默认的内置Java类型
        metaHolder.process_Primitive();
        // 扫描自定义的@BluexEnum
        metaHolder.process_Bluex_Enum(customPathConfig.getEnumScanPaths());
        // 扫描自定义的@BluexType
        metaHolder.process_Bluex_Class(customPathConfig.getClassScanPaths());
        // 解析默认的流程控制节点
        metaHolder.process_Control();
        // 扫描自定义的@BluexFunctionLib
        metaHolder.process_Bluex_Function(customPathConfig.getFunctionScanPaths());
        // 解析默认的流程控制节点
        metaHolder.process_Delegate();
    }
}
