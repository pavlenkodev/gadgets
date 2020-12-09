package tech.itpark.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.itpark.marketplace.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
