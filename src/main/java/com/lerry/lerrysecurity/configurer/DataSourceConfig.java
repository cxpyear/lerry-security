package com.lerry.lerrysecurity.configurer;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *  数据连接池
 * @author LErry.li
 * Date: 2018-06-14
 * Time: 15:30
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    @Bean(name = "dataSource")
    public DataSource dataSource(Environment environment) {
        return DruidDataSourceBuilder
                .create()
                .build(environment, "spring.datasource.druid.");
    }

}
