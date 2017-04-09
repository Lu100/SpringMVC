package com.lu.shiro.configure;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017/4/3.
 */
@ComponentScan("com.lu.shiro")
@Configuration
@EnableConfigurationProperties
@EnableAutoConfiguration
@SpringBootConfiguration
public class ApplicationConfigure {

}
