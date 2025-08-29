package com.zsb.bluex.core.launch;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "bluex")
public class CustomPathConfig {
    private List<String> enumScanPaths;
    private List<String> classScanPaths;
    private List<String> functionScanPaths;
}
