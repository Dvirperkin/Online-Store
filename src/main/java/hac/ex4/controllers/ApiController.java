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
import java.util.List;

@RestController
@RequestMapping("/api")

public class ApiController {
    @Autowired
    private ProductRepository productRepository;


    @Resource(name = "getShoppingCart")
    private ShoppingCart shoppingCart;

    /**
     * Add items to the cart
     * @param id - The id of the item
     * @param model
     * @return ResponseEntity which contains the answer of the response.
     */
    @GetMapping("/addToCart/{id}")
    public ResponseEntity addToCart(@PathVariable Long id, Model model){

        Product product = productRepository.findProductById(id);
        shoppingCart.add(product);
        model.addAttribute("message" , "Successfully Added!");
        model.addAttribute("addedState", "text-bg-success");

        return ResponseEntity.ok(HttpStatus.OK);
    }
    /**
     * Add items to the cart
     * @param name -The name of the item
     * @param model
     * @return List<Product> - The list of all products with the search name.
     */
    @GetMapping("/findProducts/{name}")
    public List<Product> findProducts(@PathVariable String name, Model model){
        List<Product> products = productRepository.findFirst5ByNameContainsOrderByName(name);
        return products;
    }
}
