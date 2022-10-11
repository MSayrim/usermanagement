package com.example.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
public class UsermanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsermanagementApplication.class, args);
	}

}
