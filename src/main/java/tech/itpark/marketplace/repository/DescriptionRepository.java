package tech.itpark.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tech.itpark.marketplace.model.Description;
@EnableJpaRepositories
public interface DescriptionRepository extends JpaRepository<Description, Long> {

}
