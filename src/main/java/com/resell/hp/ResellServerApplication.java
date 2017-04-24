package com.resell.hp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={
		DataSourceTransactionManagerAutoConfiguration.class,
		DataSourceAutoConfiguration.class
})
public class ResellServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResellServerApplication.class, args);
	}
}
