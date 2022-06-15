package hac.ex4.controllers;

import hac.ex4.beans.ShoppingCart;
import hac.ex4.database.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")

public class ApiController {
    @Autowired
    private ProductRepository productRepository;

    @Resource(name = "getShoppingCart")
    private ShoppingCart shoppingCart;

    @GetMapping("/addToCart/{id}")
    public ResponseEntity addToCart(@PathVariable Long id, Model model){
        shoppingCart.add(productRepository.getById(id));
        return ResponseEntity.ok(HttpStatus.OK);
    }

//    @GetMapping("/product/{id}")
//    public Product getProduct(@PathVariable Long id){
//        return productRepository.findProductById(id);
//    }
//
//    @GetMapping("/products")
//    public List<Product> allProduct() {
//        return productRepository.findAll();
//    }
//
//    @GetMapping("/top5products")
//    public List<Product> top5() { return productRepository.findFirst5ByOrderByDiscountDesc(); }
}
