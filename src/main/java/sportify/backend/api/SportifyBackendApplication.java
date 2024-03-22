package sportify.backend.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableMongoAuditing
public class SportifyBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(SportifyBackendApplication.class, args);
	}
}
