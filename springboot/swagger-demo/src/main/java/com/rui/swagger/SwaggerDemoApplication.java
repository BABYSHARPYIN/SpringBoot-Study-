package com.rui.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2 //开启Swagger2
public class SwaggerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwaggerDemoApplication.class, args);
	}

}
