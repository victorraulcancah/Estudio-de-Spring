package com.example.agenda_contactos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AgendaContactosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendaContactosApplication.class, args);
	}

}
