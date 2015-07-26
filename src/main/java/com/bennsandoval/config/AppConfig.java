package com.bennsandoval.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Mackbook on 7/25/15.
 */
@Configuration
@PropertySource(value = "classpath:global.properties")
public class AppConfig {

}
