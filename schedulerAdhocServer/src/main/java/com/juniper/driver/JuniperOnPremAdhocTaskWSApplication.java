package com.juniper.driver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.juniper.*")
@SpringBootApplication
public class JuniperOnPremAdhocTaskWSApplication {

	public static void main(String[] args) {
		SpringApplication.run(JuniperOnPremAdhocTaskWSApplication.class, args);
	}

}

