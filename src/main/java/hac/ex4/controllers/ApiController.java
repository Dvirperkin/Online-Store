package hac.ex4.controllers;

import hac.ex4.beans.ShoppingCart;
import hac.ex4.database.Product;
import hac.ex4.database.ProductRepository;
import hac.ex4.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")

/**
 * ApiController
 */
public class ApiController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;


    @Resource(name = "getShoppingCart")
    private ShoppingCart shoppingCart;

    /**
     * Add items to the cart
     * @param id - The id of the item
     * @param model - model
     * @param quantity - quantity
     * @return ResponseEntity which contains the answer of the response.
     */
    @GetMapping("/addToCart/{id}/{quantity}")
    public ResponseEntity addToCart(@PathVariable Long id, @PathVariable Integer quantity, Model model){
        if(quantity > 0){
            Product product = productRepository.findProductById(id);
            if(product.getStock() < 1){
                return new ResponseEntity<>(shoppingCart.getCartSize() ,HttpStatus.BAD_REQUEST);
            }
            shoppingCart.add(product, quantity);
            model.addAttribute("message" , "Successfully Added!");
            model.addAttribute("addedState", "text-bg-success");
        }

        return new ResponseEntity<>(shoppingCart.getCartSize() ,HttpStatus.OK);
    }
    /**
     * Add items to the cart
     * @param name -The name of the item
     * @param model - model
     * @return ResponseEntity - The list of all products with the search name.
     */
    @GetMapping("/findProducts/{name}")
    public ResponseEntity findProducts(@PathVariable String name, Model model){
        return new ResponseEntity<>(productRepository.findFirst5ByNameContainsOrderByName(name) ,HttpStatus.OK);
    }
}
