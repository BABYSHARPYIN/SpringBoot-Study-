package com.rui.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {
    //配置了swagger的Docket的bean实例
    @Bean
    public Docket docket(Environment environment){

        //设置swagger要显示的环境
        Profiles profiles = Profiles.of("dev");
        //获取项目的环境,通过 environment.acceptsProfiles判断自己是否处在设定的环境之中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("尹锐")
                //enable是否启动Swagger，如果为False，则Swagger不能在浏览器中访问
                .enable(flag)
                .select()
                //RequestHandlerSelectors,配置要扫描接口的方式
                //basePackage：指定要扫描的包
                //any():扫描全部
                //none():不扫描
                //withClassAnnotation：扫描类上的注解，参数是一个注解的反射对象
                //withMethodAnnotation：扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.rui.swagger.controller"))
                //paths过滤什么路径
                //.paths(PathSelectors.ant("/kuang/**"))
                .build();
    }
    //配置Swagger信息
    private ApiInfo apiInfo(){
        //作者信息
         Contact contact = new Contact("尹锐", "https://www.cnblogs.com/MrKeen/", "yinruizuishuai@gmial.com");
        return new ApiInfo(
                "尹锐的SwaggerAPI文档",
                "人生无处不青山",
                "v1.0",
                "https://www.cnblogs.com/MrKeen/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}
