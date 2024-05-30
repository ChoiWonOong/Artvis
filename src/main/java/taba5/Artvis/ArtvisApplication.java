package taba5.Artvis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ArtvisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtvisApplication.class, args);
	}

}
