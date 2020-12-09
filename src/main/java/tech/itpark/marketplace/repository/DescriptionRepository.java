package tech.itpark.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.itpark.marketplace.model.Description;

public interface DescriptionRepository extends JpaRepository<Description, Long> {

}
