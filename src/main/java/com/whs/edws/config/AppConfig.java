package com.whs.edws.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "tencent")
@Data
public class AppConfig {

    private String appId;

    private String name;

    private String age;
}
