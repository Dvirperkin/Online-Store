package hac.ex4.controllers;

import hac.ex4.database.Product;
import hac.ex4.database.ProductRepository;
import hac.ex4.database.Payment;
import hac.ex4.database.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/api")
public class AdminApiController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping(value = "/Payments")
    public List<Payment> getPayments() {
        return paymentRepository.findAllByOrderByDatetime();
    }

    @PostMapping(value = "/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, BindingResult result) {
        // Spring validation according to User @Entity definition
        if (result.hasErrors())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        product.setImage("ProductImage.png");
        productRepository.save(product);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity updateProduct(@PathVariable("id") final Long id, @Valid @RequestBody Product updatedProduct, BindingResult result) {
        if (result.hasErrors())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        Product product = productRepository.findProductById(updatedProduct.getId());
        if (product != null)
            productRepository.save(updatedProduct);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") final Long id) {
        Product product = productRepository.findProductById(id);  //.orElseThrow(()-> new IllegalArgumentException("Invalid product id: " + id));
        productRepository.delete(product);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
