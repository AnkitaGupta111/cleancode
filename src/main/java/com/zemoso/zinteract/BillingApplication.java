package com.zemoso.zinteract;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BillingApplication {

	public static void main(final String[] args) {
		SpringApplication.run(BillingApplication.class, args);
	}

}
