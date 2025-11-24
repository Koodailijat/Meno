package com.budgetapp.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class BudgetApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetApiApplication.class, args);
	}

}
