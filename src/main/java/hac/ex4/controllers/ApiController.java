package hac.ex4.controllers;

import hac.ex4.database.Product;
import hac.ex4.database.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable Long id){
        return productRepository.findProductById(id);
    }

    @GetMapping("/products")
    public List<Product> allProduct() {
        return productRepository.findAll();
    }

    @GetMapping("/top5products")
    public List<Product> top5() { return productRepository.findFirst5ByOrderByDiscountDesc(); }
}
