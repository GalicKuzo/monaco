package com.monaco.monaco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MonacoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonacoApplication.class, args);
		SpringApplication app = new SpringApplication(MonacoApplication.class);
        ConfigurableApplicationContext ctx = app.run(args);
        // Obtener el puerto que Spring Boot está usando
        String port = ctx.getEnvironment().getProperty("server.port");
        System.out.println(">>> Aplicación iniciada, puerto: " + port);
	}

}
