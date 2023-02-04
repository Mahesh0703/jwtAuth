package com.retail_syst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RetailSystApplication {

	public static void main(String[] args) {
		System.out.println("Retail system started :; ");
		SpringApplication.run(RetailSystApplication.class, args);
		
		System.out.println("Retail System shutdown :: ");
	}

}
