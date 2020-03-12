package tekup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaRepositories(basePackages = "tekup.repository")
@SpringBootApplication
@EnableScheduling
public class ProjetJeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetJeeApplication.class, args);
	}

}
