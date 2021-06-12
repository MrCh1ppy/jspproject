package com.example.jsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 橙鼠鼠
 */
@EnableTransactionManagement
@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties
public class JspprojectApplication {

	public static void main (String[] args) {
		SpringApplication.run(JspprojectApplication.class, args);
	}

}
