package hac.ex4.controllers;

import hac.ex4.beans.ShoppingCart;
import hac.ex4.database.Product;
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
        Product product = productRepository.findProductById(id);
        shoppingCart.add(product);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
