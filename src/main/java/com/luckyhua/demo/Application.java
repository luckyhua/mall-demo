package com.luckyhua.demo;

import com.luckyhua.demo.global.filter.DruidStatFilter;
import com.luckyhua.demo.global.servlet.DruidStatViewServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MapperScan("com.luckyhua.demo.dao")
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableAsync
public class Application {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(Application.class, args);
    }

    public static  ConfigurableApplicationContext getContext() {
        return context;
    }

    @Bean
    public FilterRegistrationBean druidFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        DruidStatFilter druidStatFilter = new DruidStatFilter();
        registrationBean.setFilter(druidStatFilter);
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"); //忽略资源
        registrationBean.setInitParameters(initParams);
        return registrationBean;
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        DruidStatViewServlet druidStatViewServlet = new DruidStatViewServlet();
        registrationBean.setServlet(druidStatViewServlet);
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/druid/*");
        registrationBean.setUrlMappings(urlPatterns);
        Map<String, String> initParams = new HashMap<>();
//        initParams.put("allow", "127.0.0.1,192.168.1.146"); // IP白名单 (没有配置或者为空，则允许所有访问)
//        initParams.put("deny", "192.168.1.111"); // IP黑名单 (存在共同时，deny优先于allow)
        initParams.put("loginUsername", "root"); // 用户名
        initParams.put("loginPassword", "admin"); // 密码
        initParams.put("resetEnable", "false"); // 禁用HTML页面上的“Reset All”功能
        registrationBean.setInitParameters(initParams);
        return registrationBean;
    }

}
