package com.zhw.helloworld;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.zhw.helloworld.dal")
@EnableSwagger2Doc
@EnableTransactionManagement
@EnableAspectJAutoProxy
@SpringBootApplication
@EnableDubbo
public class WebApiSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApiSpringbootApplication.class, args);
	}

}
