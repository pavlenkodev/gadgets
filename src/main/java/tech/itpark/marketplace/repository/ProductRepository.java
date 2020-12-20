package tech.itpark.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.itpark.marketplace.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query
    Iterable<Product> findByCategory(String category);

    @Query
    Iterable<Product> findByNameContainingIgnoreCase(String name);

}
