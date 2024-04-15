package com.cols.bank.transactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@SpringBootApplication
public class AppColsBankApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppColsBankApiApplication.class, args);
	}

}
