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
import tech.itpark.marketplace.service.ProductService;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public String findAll(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/product-create")
    public String createProductForm(Product product) {
        return "product-create";
    }

    @PostMapping("/product-create")
    public String createProduct(Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/product-update/{id}")
    public String updateProductForm(
            @PathVariable("id") Long id,
            Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "/product-update";
    }

    @PostMapping("/product-update")
    public String updateProduct(Product product) {
        productService.save(product);
        return "redirect:/products";
    }

//    @GetMapping("/filter-form")
//    public String filterForm(@RequestParam String fcategory,
//                             @RequestParam String fendor,
//                             @RequestParam int minPrice,
//                             @RequestParam int maxPrice,
//                             @RequestParam int minRam,
//                             @RequestParam int maxRam,
//                             @RequestParam String cpu,
//                             @RequestParam int minStorage){
//        return ("/filter-form");
//    }

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

        final var filters = productService.filter(category, vendor, minPrice, maxPrice, minRam, maxRam, cpu, minStorage, maxStorage);
        model.addAttribute("filters", filters);

//       model.addAttribute("filters", filters);
//          filters = .productService.findAll();
        return "/product-filter";
    }

    @GetMapping("/category-smartphones")
    public String findByCategorySmartphones(String category, Model model) {
        category = "smartphones";
        final var byCategory = productService.findByCategory(category);
        model.addAttribute("byCategory", byCategory);

        return "/category-smartphones";
    }

    @GetMapping("/category-tablets")
    public String findByCategoryTablets(String category, Model model) {
        category = "tablets";
        final var byCategory = productService.findByCategory(category);
        model.addAttribute("byCategory", byCategory);

        return "/category-tablets";

    }

    @PostMapping("/search-by-name")
    public String searchByName(@RequestParam String name, Model model) {
        var byNameIgnoreCase = productService.findByNameIgnoreCase(name);
        model.addAttribute("byNameIgnoreCase", byNameIgnoreCase);
        return "/search-by-name";
    }

    @GetMapping("/category-laptops")
    public String findByCategoryLaptops(String category, Model model) {
        category = "laptops";
        final var byCategory = productService.findByCategory(category);
        model.addAttribute("byCategory", byCategory);

        return "/category-laptops";
    }


    @GetMapping("product-delete/{id}")
    public String removeById(@PathVariable("id") Long id) {
        productService.removeById(id);
        return "redirect:/products";
    }
}
