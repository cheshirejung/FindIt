package com.FindIt.FindIt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "userAuditorAware")
@SpringBootApplication
public class FindItApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindItApplication.class, args);
	}

}
