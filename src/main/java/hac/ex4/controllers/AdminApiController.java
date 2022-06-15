package hac.ex4.controllers;

import hac.ex4.database.Product;
import hac.ex4.database.ProductRepository;
import hac.ex4.database.Payment;
import hac.ex4.database.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

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
        if (product != null)
            productRepository.save(product);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
        model.addAttribute("tabActive", "update");
        model.addAttribute("products", productRepository.findAll());
        return "admin";
    }

    @PostMapping(value = "/delete")
    public String delete(@Valid final Long id,BindingResult result, Model model) {
        if(result.hasErrors())
            return "404";
        Product product = productRepository.findProductById(id);  //.orElseThrow(()-> new IllegalArgumentException("Invalid product id: " + id));
        productRepository.delete(product);
        model.addAttribute("tabActive", "delete");
        model.addAttribute("products", productRepository.findAll());
        return "admin";
    }
}
