package com.lu.springboot.config;

import com.lu.springboot.args.UserResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description:</b>
 *
 * <b>Author:</b> Luyongjia
 * <b>Date:</b> 2017年03月08日  14:29
 * <b>Copyright:</b> Copyright ©2016 tempus.cn. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   Date                             Author                Detail
 *   ----------------------------------------------------------------------
 *   0.1   2017年03月08日  14:29   Luyongjia
 *         new file.
 * </pre>
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new UserResolver());
        super.addArgumentResolvers(argumentResolvers);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
        registry.viewResolver(internalResourceViewResolver);
        super.configureViewResolvers(registry);
    }
}
