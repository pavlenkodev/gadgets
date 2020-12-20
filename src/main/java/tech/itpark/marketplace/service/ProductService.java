package tech.itpark.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.itpark.marketplace.model.Product;
import tech.itpark.marketplace.repository.ProductRepository;
import tech.itpark.marketplace.specifications.ProductSearchCriteria;
import tech.itpark.marketplace.specifications.ProductSpecification;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Long id) {
        return productRepository.getOne(id);
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }


    public void removeById(Long id) {
        productRepository.deleteById(id);
    }

    public Iterable<Product> findByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public Iterable<Product> findByNameIgnoreCase(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> filter(String category,
                                String vendor,
                                String minPrice,
                                String maxPrice,
                                String minRam,
                                String maxRam,
                                String cpu,
                                String minStorage,
                                String maxStorage) {

        int parsMinPrice = 0;
        int parsMaxPrice = 7000000;
        int parsMinRam = 1;
        int parsMaxRam = 64;
        int parsMinStorage = 1;
        int parsMaxStorage = 1024;

        if (!minPrice.isBlank()) {
            parsMinPrice = Integer.parseInt(minPrice);
        }
        if (!maxPrice.isBlank()) {
            parsMaxPrice = Integer.parseInt(maxPrice);
        }
        if (!minRam.isBlank()) {
            parsMinRam = Integer.parseInt(minRam);
        }
        if (!maxRam.isBlank()) {
            parsMaxRam = Integer.parseInt(maxRam);
        }
        if (!minStorage.isBlank()) {
            parsMinStorage = Integer.parseInt(minStorage);
        }
        if (!maxStorage.isBlank()) {
            parsMaxStorage = Integer.parseInt(maxStorage);
        }

        ProductSpecification spec1 =
                new ProductSpecification(new ProductSearchCriteria("category", ":", category));
        ProductSpecification spec2 =
                new ProductSpecification(new ProductSearchCriteria("vendor", ":", vendor));
        ProductSpecification spec3 =
                new ProductSpecification(new ProductSearchCriteria("price", ">", parsMinPrice));
        ProductSpecification spec4 =
                new ProductSpecification(new ProductSearchCriteria("price", "<", parsMaxPrice));
        ProductSpecification spec5 =
                new ProductSpecification(new ProductSearchCriteria("ram", ">", parsMinRam));
        ProductSpecification spec6 =
                new ProductSpecification(new ProductSearchCriteria("ram", "<", parsMaxRam));
        ProductSpecification spec7 =
                new ProductSpecification(new ProductSearchCriteria("cpu", ":", cpu));
        ProductSpecification spec8 =
                new ProductSpecification(new ProductSearchCriteria("storage", ">", parsMinStorage));
        ProductSpecification spec9 =
                new ProductSpecification(new ProductSearchCriteria("storage", "<", parsMaxStorage));

        return productRepository.findAll(Specification.where(spec1).and(spec2).and(spec3).and(spec4).and(spec5).and(spec6).and(spec7).and(spec8).and(spec9));
    }
}

