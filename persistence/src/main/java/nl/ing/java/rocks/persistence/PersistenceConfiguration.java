package nl.ing.java.rocks.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "nl.ing.java.rocks.persistence")
@EntityScan(basePackages = "nl.ing.java.rocks.entities")
class PersistenceConfiguration {
}
