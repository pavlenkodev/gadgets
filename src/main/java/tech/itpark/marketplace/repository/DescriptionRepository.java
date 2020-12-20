package tech.itpark.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.itpark.marketplace.model.Description;

@Repository
public interface DescriptionRepository extends JpaRepository<Description, Long> {

}
