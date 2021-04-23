package com.zhw.helloworld;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zhw.helloworld.dal")
@EnableSwagger2Doc
@SpringBootApplication
public class WebApiSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApiSpringbootApplication.class, args);
	}

}
