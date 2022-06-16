package hac.ex4.controllers;

import hac.ex4.database.Payment;
import hac.ex4.database.PaymentRepository;
import hac.ex4.database.Product;
import hac.ex4.database.ProductRepository;
import hac.ex4.services.PaymentService;
import hac.ex4.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("top5products", productService.findFirst5ByOrderByDiscountDesc());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("message" , "");
        model.addAttribute("addedState" , "");
        return "index";
    }

    @GetMapping("/product/{id}")
    public String product(@PathVariable Long id, Model model){
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping("/searchProducts")
    public String searchProducts(String name, Model model){
        name = name.trim();

        List<Product> products = productService.findAllByNameContainsOrderByName(name);
        model.addAttribute("products", products);
        if(products.size() != 0)
            model.addAttribute("message","Search Results");
        else {
            model.addAttribute("message","No results for the product you entered");
        }
        return "search";
    }
}
