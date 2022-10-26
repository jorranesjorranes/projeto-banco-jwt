package com.estudo.bancoprojeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class BancoProjetoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoProjetoApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("senha123"));
	}

	@GetMapping("/")
	public String index(){
		return "Olá Mundo!";
	}
}
