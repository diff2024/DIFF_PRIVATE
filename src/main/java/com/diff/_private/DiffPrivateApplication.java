package com.diff._private;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DiffPrivateApplication extends SpringBootServletInitializer {
	
	//WAR 배포 설정
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DiffPrivateApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DiffPrivateApplication.class, args);
	}

}
