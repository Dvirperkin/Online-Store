package hac.ex4.services;

import hac.ex4.database.Product;
import hac.ex4.database.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void save(Product product){
        productRepository.save(product);
    }

    public void delete(Product product){
        productRepository.delete(product);
    }

    public Product findProductById(Long id){
        return productRepository.findProductById(id);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public List<Product> findAllByNameContainsOrderByName(String name){
        return productRepository.findAllByNameContainsOrderByName(name);
    }

    public List<Product> findFirst5ByOrderByDiscountDesc(){
        return productRepository.findFirst5ByOrderByDiscountDesc();
    }

    @Transactional
    public void executePurchase(HashMap<Long, Product> cartProducts, HashMap<Long, Integer> cartQuantities){
        cartProducts.forEach((id, product) -> {
            Product updatedProduct = productRepository.findProductById(id);
            updatedProduct.setStock(productRepository.findProductById(id).getStock() - cartQuantities.get(id));
        });
    }
}
