package hac.ex4.controllers;

import hac.ex4.beans.ShoppingCart;
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

import javax.annotation.Resource;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @Resource(name = "getShoppingCart")
    private ShoppingCart shoppingCart;

    /**
     * Gets the main page.
     * @param model
     * @return index - Home page of the site.
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("top5products", productService.findFirst5ByOrderByDiscountDesc());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("message" , "");
        model.addAttribute("addedState" , "");
        model.addAttribute("cartQuantity", shoppingCart.getCartSize());
        return "index";
    }

    /**
     * Opens product page with the chosen id.
     * @param id - The id of the chosen product .
     * @param model
     * @return product - The Product page.
     */
    @GetMapping("/product/{id}")
    public String product(@PathVariable Long id, Model model){
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("cartQuantity", shoppingCart.getCartSize());
        return "product";
    }

    /**
     * Opens the admin page, the page is protected by spring security authentications.
     * @param model
     * @return admin - The admin page.
     */
    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("tabActive", "");
        model.addAttribute("product", new Product());
        model.addAttribute("payment", new Payment());
        model.addAttribute("payments", paymentService.findAll());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("cartQuantity", shoppingCart.getCartSize());
        return "admin";
    }


    /**
     * The results of user search.
     * @param name - A sub string that name can contain.
     * @param model
     * @return search - The result page.
     */
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
        model.addAttribute("cartQuantity", shoppingCart.getCartSize());
        return "search";
    }
}
