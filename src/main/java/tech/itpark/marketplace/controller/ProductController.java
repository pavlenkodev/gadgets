package tech.itpark.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.itpark.marketplace.model.Product;
import tech.itpark.marketplace.repository.ProductRepository;
import tech.itpark.marketplace.manager.ProductManager;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    private final ProductManager productManager;
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductManager productManager, ProductRepository productRepository) {
        this.productManager = productManager;
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public String findAll(Model model) {
        List<Product> products = productManager.findAll();
        model.addAttribute("products", products);
        return "product/product-list";
    }

    @GetMapping("/product-create")
    public String createProductForm(Product product) {
        return "product/product-create";
    }

    @PostMapping("/product-create")
    public String createProduct(Product product) {
        productManager.save(product);
        return "redirect:/products";
    }

    @GetMapping("/product-update/{id}")
    public String updateProductForm(
            @PathVariable("id") Long id,
            Model model) {
        Product product = productManager.findById(id);
        model.addAttribute("product", product);
        return "product/product-update";
    }

    @PostMapping("/product-update")
    public String updateProduct(Product product) {
        productManager.save(product);
        return "redirect:/products";
    }

    @PostMapping("/product-filter")
    public String filter(@RequestParam String category,
                         @RequestParam String vendor,
                         @RequestParam int minPrice,
                         @RequestParam int maxPrice,
                         @RequestParam int minRam,
                         @RequestParam int maxRam,
                         @RequestParam String cpu,
                         @RequestParam int minStorage,
                         @RequestParam int maxStorage,
                         Model model
    ) {

        final var filters = productManager.filter(category, vendor, minPrice, maxPrice, minRam, maxRam, cpu, minStorage, maxStorage);
        model.addAttribute("filters", filters);

        return "product/product-filter";
    }

    @GetMapping("/category-smartphones")
    public String findByCategorySmartphones(String category, Model model) {
        category = "smartphones";
        final var byCategory = productManager.findByCategory(category);
        model.addAttribute("byCategory", byCategory);

        return "category/category-smartphones";
    }

    @GetMapping("/category-tablets")
    public String findByCategoryTablets(String category, Model model) {
        category = "tablets";
        final var byCategory = productManager.findByCategory(category);
        model.addAttribute("byCategory", byCategory);

        return "category/category-tablets";

    }

    @PostMapping("/search-by-name")
    public String searchByName(@RequestParam String name, Model model) {
        var byNameIgnoreCase = productManager.findByNameIgnoreCase(name);
        model.addAttribute("byNameIgnoreCase", byNameIgnoreCase);
        return "product/search-by-name";
    }

    @GetMapping("/category-laptops")
    public String findByCategoryLaptops(String category, Model model) {
        category = "laptops";
        final var byCategory = productManager.findByCategory(category);
        model.addAttribute("byCategory", byCategory);

        return "category/category-laptops";
    }


    @GetMapping("product-delete/{id}")
    public String removeById(@PathVariable("id") Long id) {
        productManager.removeById(id);
        return "redirect:/products";
    }

    @GetMapping("product-full/{id}")
    public String fullView(@PathVariable long id, Model model){
//        List<Product> products = new ArrayList<>();
        final var fullView = productManager.findById(id);
        model.addAttribute("fullView", fullView);

        return "product/product-full";
    }
}
