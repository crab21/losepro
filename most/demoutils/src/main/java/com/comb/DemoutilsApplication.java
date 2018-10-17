package com.comb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DemoutilsApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoutilsApplication.class, args);
	}

	@GetMapping("/hello")
	public String name() {
		new TeRecon().make();
        System.out.println("ok");
        return "ok";
    }
}
