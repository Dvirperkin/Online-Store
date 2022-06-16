package hac.ex4.controllers;

import hac.ex4.beans.ShoppingCart;
import hac.ex4.database.Payment;
import hac.ex4.database.PaymentRepository;
import hac.ex4.database.Product;
import hac.ex4.services.PaymentService;
import hac.ex4.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.HashMap;

@Controller
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @Resource(name = "getShoppingCart")
    private ShoppingCart shoppingCart;

    /**
     * The cart page
     * @param id - The id of product
     * @param model - model
     * @return - Cart page
     */
    @GetMapping("/cart")
    public String cart(Long id, Model model){

        model.addAttribute("total", shoppingCart.getTotalPrice());
        model.addAttribute("quantities", new HashMap<Long, Integer>());
        model.addAttribute("products", new HashMap<Long, Product>());
        model.addAttribute("quantities", shoppingCart.getQuantities());
        model.addAttribute("products", shoppingCart.getProducts());
        model.addAttribute("cartQuantity", shoppingCart.getCartSize());

        return "cart";
    }

    /**
     * Checks out the cart.
     * @param model - model
     * @return The cart page
     */
    @GetMapping("/checkout")
    public String checkout(Model model){

        HashMap<Long, Product> cartProducts = shoppingCart.getProducts();
        HashMap<Long, Integer> cartQuantities = shoppingCart.getQuantities();

        try {
            if(!cartProducts.isEmpty()) {
                productService.executePurchase(cartProducts, cartQuantities);

                Payment payment = new Payment();
                payment.setAmount(shoppingCart.getTotalPrice());
                paymentService.save(payment);

                shoppingCart.clear();
            }
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            System.out.println(e);
        } finally {
            model.addAttribute("total", shoppingCart.getTotalPrice());
            model.addAttribute("quantities", shoppingCart.getQuantities());
            model.addAttribute("products", new HashMap<Long, Product>());
            model.addAttribute("products", shoppingCart.getProducts());
        }
        model.addAttribute("cartQuantity", shoppingCart.getCartSize());
        return "cart";
    }

    /**
     * Clears the cart from products
     * @param model - model
     * @return - The cart page
     */
    @GetMapping("/clearCart")
    public String clearCart(Model model){
        shoppingCart.clear();
        model.addAttribute("total", shoppingCart.getTotalPrice());
        model.addAttribute("quantities", shoppingCart.getQuantities());
        model.addAttribute("products", new HashMap<Long, Product>());
        model.addAttribute("products", shoppingCart.getProducts());

        model.addAttribute("cartQuantity", shoppingCart.getCartSize());
        return "redirect:/cart";
    }

    /**
     * Removes product from card.
     * @param id - The id of the desire product
     * @param model - model
     * @return The cart page.
     */
    @GetMapping("/removeFromCart/{id}")
    public String removeFromCart(@PathVariable Long id, Model model){

        shoppingCart.delete(id);
        model.addAttribute("total", shoppingCart.getTotalPrice());
        model.addAttribute("products", new HashMap<Long, Product>());
        model.addAttribute("products", shoppingCart.getProducts());
        model.addAttribute("quantities", shoppingCart.getQuantities());
        model.addAttribute("cartQuantity", shoppingCart.getCartSize());
        return "redirect:/cart";
    }
}
