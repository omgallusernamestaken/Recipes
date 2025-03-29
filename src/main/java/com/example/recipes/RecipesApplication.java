package com.example.recipes;

import com.example.recipes.entities.AppTester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipesApplication implements CommandLineRunner {

	@Autowired
	private AppTester appTester;

	public static void main(String[] args) {
		SpringApplication.run(RecipesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Uruchomienie testu
		appTester.testApp();
	}
}
