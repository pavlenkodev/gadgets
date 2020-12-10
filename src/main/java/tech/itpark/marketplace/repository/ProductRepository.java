package tech.itpark.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.itpark.marketplace.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query
            ("select p from Product p where p.category  = :category and p.vendor = :vendor and p.price between :minPrice and :maxPrice and p.ram between :minRam and :maxRam and p.cpu = :cpu and p.storage between :minStorage and :maxStorage")
    List<Product> filter(@Param("category") String category,
                         @Param("vendor") String vendor,
                         @Param("minPrice") int minPrice,
                         @Param("maxPrice") int maxPrice,
                         @Param("minRam") int minRam,
                         @Param("maxRam") int maxRam,
                         @Param("cpu") String cpu,
                         @Param("minStorage") int minStorage,
                         @Param("maxStorage") int maxStorage
    );

    List<Product> findByCategory(String category);

    List<Product> findByNameContainingIgnoreCase(String name);

    Product findByDescriptionId(Product product);
}
