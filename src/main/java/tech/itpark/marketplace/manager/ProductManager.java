package tech.itpark.marketplace.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.itpark.marketplace.model.Product;
import tech.itpark.marketplace.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductManager {
    private final ProductRepository productRepository;
    private List<Optional<Product>> toBasket = new ArrayList<Optional<Product>>();

    @Autowired
    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Long id) {
        return productRepository.getOne(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }


    public void removeById(Long id) {
        productRepository.deleteById(id);
    }


    public List<Product> findByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> findByNameIgnoreCase(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> filter(String category,
                                String vendor,
                                int minPrice,
                                int maxPrice,
                                int minRam,
                                int maxRam,
                                String cpu,
                                int minStorage,
                                int maxStorage) {
        category = category.toLowerCase();
        vendor = vendor.toLowerCase();
        cpu = cpu.toLowerCase();
        return productRepository.filter(category, vendor, minPrice, maxPrice, minRam, maxRam, cpu, minStorage, maxStorage);
    }
}

