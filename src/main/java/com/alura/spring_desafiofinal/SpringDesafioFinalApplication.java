package com.alura.spring_desafiofinal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDesafioFinalApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringDesafioFinalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.inicia();
	}

}
