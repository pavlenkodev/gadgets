package tech.itpark.marketplace.service;

import org.springframework.stereotype.Service;
import tech.itpark.marketplace.model.Product;
import tech.itpark.marketplace.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    //    @Autowired
    public ProductService(ProductRepository productRepository) {
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

    public List<Product> filter(String category,
                                String vendor,
                                int minPrice,
                                int maxPrice,
                                int minRam,
                                int maxRam,
                                String cpu,
                                int minStorage,
                                int maxStorage
    ) {
        List<Product> result = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            if (!product.getCategory().toLowerCase().equals(category.toLowerCase())) {
                continue;
            }
            if (!product.getVendor().toLowerCase().equals(vendor.toLowerCase())) {
                continue;
            }
            if (product.getPrice() < minPrice) {
                continue;
            }
            if (product.getPrice() > maxPrice) {
                continue;
            }
            if (product.getRam() < minRam) {
                continue;
            }
            if (product.getRam() > maxRam) {
                continue;
            }
            if (!product.getCpu().toLowerCase().equals(cpu.toLowerCase())) {
                continue;
            }
            if (product.getStorage() < minStorage) {
                continue;
            }
            if (product.getStorage() > maxStorage) {
                continue;
            }
            result.add(product);
        }
        return result;
    }
}
