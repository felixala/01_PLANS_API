package com.felixlaura.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * This class will load messages from application.yml
 */

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "plan-api") // name application.yml to link this class
public class AppProperties {

    Map<String, String> messages = new HashMap<>();
}
