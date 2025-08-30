package com.zsb.bluex.core.job.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamicWebSocketHandlerConfig {

    @Bean
    public DynamicWebSocketHandlerMapping dynamicWebSocketHandlerMapping() {
        return new DynamicWebSocketHandlerMapping();
    }
}
