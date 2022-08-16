package com.d108.sduty;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableAspectJAutoProxy
@EnableScheduling
@SpringBootApplication
@EnableAutoConfiguration
@MapperScan(basePackages = "com.d108.sduty.repo")
public class SdutyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SdutyServerApplication.class, args);
	}

}
