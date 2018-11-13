package com.xingniu.zoon;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ZoonApplication extends SpringBootServletInitializer {

	private static ConfigurableApplicationContext context;

	@Override protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ZoonApplication.class);
	}

	public static void main(String[] args) {

		context = SpringApplication.run(ZoonApplication.class, args);

	}
}
