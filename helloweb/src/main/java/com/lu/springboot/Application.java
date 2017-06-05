package com.lu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description:</b>
 *
 * <b>Author:</b> Luyongjia
 * <b>Date:</b> 2017年03月08日  11:12
 * <b>Changelog:</b>
 *   Ver   Date                             Author                Detail
 *   ----------------------------------------------------------------------
 *   0.1   2017年03月08日  11:12   Luyongjia
 *         new file.
 * </pre>
 */
@SpringBootApplication
@EnableAutoConfiguration
public class Application {
    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder().sources(Application.class).web(true).run(args);
    }
}




