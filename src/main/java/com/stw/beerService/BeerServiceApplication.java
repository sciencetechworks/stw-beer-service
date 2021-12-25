package com.stw.beerService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"com.stw.beerService","com.stw.beerService.services"})
public class BeerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeerServiceApplication.class, args);
	}

}
