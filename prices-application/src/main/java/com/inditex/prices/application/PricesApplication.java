package com.inditex.prices.application;

import com.inditex.prices.application.constant.Package;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = Package.PRICES_PACKAGE)
@EntityScan(basePackages = Package.ENTITY_PACKAGE)
@EnableJpaRepositories(basePackages = Package.REPOSITORY_PACKAGE)
public class PricesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PricesApplication.class, args);
	}
}
