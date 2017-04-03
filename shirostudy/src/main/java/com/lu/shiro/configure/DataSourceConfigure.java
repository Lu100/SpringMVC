package com.lu.shiro.configure;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/4/3.
 */
@Component("druidDatasource")
@PropertySource("classpath:datasource.properties")
@ConfigurationProperties("druid")
@MapperScan(value = "**.mapper.*")
public class DataSourceConfigure extends DruidDataSource {

}
