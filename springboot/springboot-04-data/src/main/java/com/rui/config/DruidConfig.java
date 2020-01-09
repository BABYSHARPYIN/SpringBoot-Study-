package com.rui.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }
    //后台监控功能
    //因为SpringBoot内置了servlet容器，没有web.xml,替代方法ServletRegistrationBean
    @Bean
    public ServletRegistrationBean StatViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //后台需要有人登陆，账号密码配置
        HashMap<String, String> initParameters = new HashMap<>();
        //增加配置
        initParameters.put("loginUsername","admin");//登陆的key是固定的 loginUsername loginPassword
        initParameters.put("loginPassword","123456");

        //允许谁可以访问
        initParameters.put("allow","");//为空则所有人可以访问
        //禁止谁访问
//        initParameters.put("yinrui","192.168.1.124");

        bean.setInitParameters(initParameters);
        return bean;
    }
    //filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        //可以过滤哪些请求呢？
        Map<String,String> initParameters = new HashMap<>();
        bean.setInitParameters(initParameters);
        //这些东西不进行统计
        initParameters.put("exclusions","*.js,*.css,/druid/*");
        return bean;
    }

}
