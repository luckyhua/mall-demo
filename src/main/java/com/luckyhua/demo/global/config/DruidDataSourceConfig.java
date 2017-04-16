package com.luckyhua.demo.global.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author luckyhua
 * @date 2016/11/18
 * @description Druid的DataResource配置类
 */
@Configuration
@EnableTransactionManagement
public class DruidDataSourceConfig implements EnvironmentAware {

    private Environment environment;

    private static final Logger log = LoggerFactory.getLogger(DruidDataSourceConfig.class);

    private RelaxedPropertyResolver propertyResolver;

    public void setEnvironment(Environment env) {
        this.environment = env;
        this.propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource dataSource() {
        log.info("ZGH10000: inject druid database connection pool into spring boot container .");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(propertyResolver.getProperty("url"));
        datasource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));
        datasource.setUsername(propertyResolver.getProperty("username"));
        datasource.setPassword(propertyResolver.getProperty("password"));
        datasource.setInitialSize(Integer.valueOf(propertyResolver.getProperty("initial-size")));
        datasource.setMinIdle(Integer.valueOf(propertyResolver.getProperty("min-idle")));
        datasource.setMaxWait(Long.valueOf(propertyResolver.getProperty("max-wait")));
        datasource.setMaxActive(Integer.valueOf(propertyResolver.getProperty("max-active")));
        datasource.setMinEvictableIdleTimeMillis(Long.valueOf(propertyResolver.getProperty("min-evictable-idle-time-millis")));
        datasource.setTimeBetweenEvictionRunsMillis(Long.valueOf(propertyResolver.getProperty("time-between-eviction-runs-millis")));
        datasource.setPoolPreparedStatements(Boolean.parseBoolean(propertyResolver.getProperty("poolPreparedStatements")));
        datasource.setTestOnBorrow(Boolean.parseBoolean(propertyResolver.getProperty("test-on-borrow")));
        datasource.setTestOnReturn(Boolean.parseBoolean(propertyResolver.getProperty("test-on-return")));
        datasource.setTestWhileIdle(Boolean.parseBoolean(propertyResolver.getProperty("test-while-idle")));
        try {
			datasource.setFilters(propertyResolver.getProperty("filters"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return datasource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource());
    }

}
