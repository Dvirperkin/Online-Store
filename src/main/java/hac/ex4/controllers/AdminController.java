package hac.ex4.controllers;

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

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/")
    public String admin(Model model){
        model.addAttribute("tabActive", "");
        model.addAttribute("product", new Product());
        model.addAttribute("payment", new Payment());
        model.addAttribute("payments", paymentService.findAll());
        model.addAttribute("products", productService.findAll());
        return "admin";
    }

    @GetMapping(value = "/Payments")
    public String getPayments(Model model) {

        model.addAttribute("payments", paymentService.findAllByOrderByDatetime());

        return "admin";
    }

    @PostMapping(value = "/add")
    public String addProduct(@Valid Product product, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            product.setImage("ProductImage.png");
            productService.save(product);
        }

        model.addAttribute("tabActive", "add");
        model.addAttribute("products", productService.findAll());

        return "admin";
    }

    @PostMapping(value = "/update")
    public String updateProduct(@Valid Product product, BindingResult result, Model model) {
        if (!result.hasErrors()){
            productService.save(product);
        }

        model.addAttribute("tabActive", "update");
        model.addAttribute("products", productService.findAll());

        return "admin";
    }

    @PostMapping(value = "/delete")
    public String delete(Long id, Model model) {

        Product product = productService.findProductById(id);

        if(product == null){
            //Handle Error
        }

        productService.delete(product);
        model.addAttribute("tabActive", "delete");
        model.addAttribute("products", productService.findAll());

        return "admin";
    }
}
