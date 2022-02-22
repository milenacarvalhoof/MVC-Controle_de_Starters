package com.gft.controleStartersGFT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ControleStartersGftApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleStartersGftApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("Gft2021"));
		System.out.println(new BCryptPasswordEncoder().encode("0000"));
	}

}
