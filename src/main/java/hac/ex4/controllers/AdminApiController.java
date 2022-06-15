package hac.ex4.controllers;

import hac.ex4.database.PaymentRepository;
import hac.ex4.database.Product;
import hac.ex4.database.ProductRepository;
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
public class AdminApiController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping(value = "/Payments")
    public String getPayments(Model model) {

        model.addAttribute("payments", paymentRepository.findAll());

        return "admin";
    }

    @PostMapping(value = "/add")
    public String addProduct(@Valid Product product, BindingResult result, Model model) {
        // Spring validation according to User @Entity definition
        if (result.hasErrors())
            return "404";

        product.setImage("ProductImage.png");
        productRepository.save(product);

        model.addAttribute("tabActive", "add");
        model.addAttribute("products", productRepository.findAll());

        return "admin";
    }

    @PostMapping(value = "/update")
    public String updateProduct(@Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors())
            return "404";

        Product updatedProduct = productRepository.findProductById(product.getId());
        
        productRepository.save(product);

        model.addAttribute("tabActive", "update");
        model.addAttribute("products", productRepository.findAll());

        return "admin";
    }

    @PostMapping(value = "/delete")
    public String delete(Long id, Model model) {

        Product product = productRepository.findProductById(id);
        productRepository.delete(product);
        model.addAttribute("tabActive", "delete");
        model.addAttribute("products", productRepository.findAll());

        return "admin";
    }
}
