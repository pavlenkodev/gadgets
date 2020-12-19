package tech.itpark.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.itpark.marketplace.manager.ProductManager;
import tech.itpark.marketplace.model.Product;
import tech.itpark.marketplace.repository.ProductRepository;

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
        model.addAttribute("products", productManager.findAll());
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
    public String updateProductForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productManager.findById(id));
        return "product/product-update";
    }

    @PostMapping("/product-update")
    public String updateProduct(Product product) {
        productManager.save(product);
        return "redirect:/products";
    }

    @PostMapping("/product-filter")
    public String filter(@RequestParam(required = false) String category,
                         @RequestParam(required = false) String vendor,
                         @RequestParam(required = false) String minPrice,
                         @RequestParam(required = false) String maxPrice,
                         @RequestParam(required = false) String minRam,
                         @RequestParam(required = false) String maxRam,
                         @RequestParam(required = false) String cpu,
                         @RequestParam(required = false) String minStorage,
                         @RequestParam(required = false) String maxStorage,
                         Model model
    ) {
        model.addAttribute("filters", productManager.filter(category, vendor, minPrice, maxPrice, minRam, maxRam, cpu, minStorage, maxStorage));
        return "product/product-filter";
    }

    @GetMapping("/category-smartphones")
    public String findByCategorySmartphones(String category, Model model) {
        category = "smartphones";
        model.addAttribute("byCategory", productManager.findByCategory(category));
        return "category/category-smartphones";
    }

    @GetMapping("/category-tablets")
    public String findByCategoryTablets(String category, Model model) {
        category = "tablets";
        model.addAttribute("byCategory", productManager.findByCategory(category));
        return "category/category-tablets";

    }

    @GetMapping("/category-laptops")
    public String findByCategoryLaptops(String category, Model model) {
        category = "laptops";
        model.addAttribute("byCategory", productManager.findByCategory(category));
        return "category/category-laptops";
    }

    @PostMapping("/search-by-name")
    public String searchByName(@RequestParam String name, Model model) {
        model.addAttribute("byNameIgnoreCase", productManager.findByNameIgnoreCase(name));
        return "product/search-by-name";
    }


    @GetMapping("product-delete/{id}")
    public String removeById(@PathVariable("id") Long id) {
        productManager.removeById(id);
        return "redirect:/products";
    }

    @GetMapping("product-full/{id}")
    public String fullView(@PathVariable long id, Model model) {
        model.addAttribute("fullView", productManager.findById(id));
        return "product/product-full";
    }
}
