package uk.pallas.typr.runnable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("uk.pallas.typr.domain")
@EntityScan("uk.pallas.typr.entities.v1.domain")
@ComponentScan("uk.pallas.typr")
public class Application {
  public static void main(final String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
