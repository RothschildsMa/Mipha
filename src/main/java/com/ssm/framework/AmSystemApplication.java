package com.ssm.framework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.ssm.framework")
@SpringBootApplication
public class AmSystemApplication {

	//hello15
	public static void main(String[] args) {
		SpringApplication.run(AmSystemApplication.class, args);
	}

}
