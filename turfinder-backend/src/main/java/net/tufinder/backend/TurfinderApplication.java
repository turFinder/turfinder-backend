package net.tufinder.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TurfinderApplication {
	public static void main(String[] args) {
		SpringApplication.run(TurfinderApplication.class, args);
	}

}
