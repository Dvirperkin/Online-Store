package hac.ex4.controllers;

import hac.ex4.beans.ShoppingCart;
import hac.ex4.database.Payment;
import hac.ex4.database.PaymentRepository;
import hac.ex4.database.Product;
import hac.ex4.database.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Resource(name = "getShoppingCart")
    private ShoppingCart shoppingCart;

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("product", new Product());
        model.addAttribute("top5products", productRepository.findFirst5ByOrderByDiscountDesc());
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("message" , "");
        model.addAttribute("addedState" , "");
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("tabActive", "");
        model.addAttribute("product", new Product());
        model.addAttribute("payment", new Payment());
        model.addAttribute("payments", paymentRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        return "admin";
    }

    @GetMapping("/cart")
    public String cart(Long id, Model model){

        model.addAttribute("products", new HashMap<Long, Product>());
        model.addAttribute("products", shoppingCart.getProducts());

        return "cart";
    }
    @GetMapping("/product/{id}")
    public String product(@PathVariable Long id, Model model){
        Product product = productRepository.getById(id);
        model.addAttribute("product", product);
        return "product";
    }
    @GetMapping("/searchProducts")
    public String searchProducts(String name, Model model){
        List<Product> products = productRepository.findAllByNameContainsOrderByName(name);
        model.addAttribute("products", products);
        if(products.size() != 0)
            model.addAttribute("message","Search Results");
        else {
            model.addAttribute("message","No results for the product you entered");
        }
        return "search";
    }
}
