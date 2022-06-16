package hac.ex4.controllers;

import hac.ex4.beans.ShoppingCart;
import hac.ex4.database.Payment;
import hac.ex4.database.Product;
import hac.ex4.services.PaymentService;
import hac.ex4.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @Resource(name = "getShoppingCart")
    private ShoppingCart shoppingCart;

    /**
     * Gets The payments table.
     * @param model
     * @return admin - The admin html page.
     */
    @GetMapping(value = "/Payments")
    public String getPayments(Model model) {

        model.addAttribute("payments", paymentService.findAllByOrderByDatetime());
        model.addAttribute("cartQuantity", shoppingCart.getCartSize());
        return "admin";
    }

    /**
     * Protect the page from get requests.
     * @return 404
     */
    @GetMapping(value = "/add")
    public String getAddProduct(){
        return "404";
    }

    /**
     * Add product to the store.
     * @param product - The product we want to add.
     * @param result
     * @param model
     * @return admin - The admin html page.
     */
    @PostMapping(value = "/add")
    public String addProduct(@Valid Product product, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            product.setImage("ProductImage.png");
            productService.save(product);
        }

        model.addAttribute("tabActive", "add");
        model.addAttribute("products", productService.findAll());
        model.addAttribute("cartQuantity", shoppingCart.getCartSize());

        return "admin";
    }

    /**
     * Protect the page from get requests.
     * @return 404
     */
    @GetMapping(value = "/update")
    public String getUpdateProduct(){
        return "404";
    }

    /**
     * Updates product that already inside the store.
     * @param product - The product we want to update.
     * @param result
     * @param model
     * @return admin - The admin html page.
     */
    @PostMapping(value = "/update")
    public String updateProduct(@Valid Product product, BindingResult result, Model model) {
        if (!result.hasErrors()){
            productService.save(product);
        }

        model.addAttribute("cartQuantity", shoppingCart.getCartSize());
        model.addAttribute("tabActive", "update");
        model.addAttribute("products", productService.findAll());

        return "admin";
    }

    /**
     * Protect the page from get requests.
     * @return 404
     */
    @GetMapping(value = "/delete")
    public String getDelete(){
        return "404";
    }

    /**
     * Deletes item from the store
     * @param id - The product we want to delete.
     * @param model
     * @return admin - The admin html page.
     */
    @PostMapping(value = "/delete")
    public String delete(Long id, Model model) {

        Product product = productService.findProductById(id);

        if(product == null){
            //Handle Error
        }

        productService.delete(product);
        model.addAttribute("cartQuantity", shoppingCart.getCartSize());
        model.addAttribute("tabActive", "delete");
        model.addAttribute("products", productService.findAll());

        return "admin";
    }
}
