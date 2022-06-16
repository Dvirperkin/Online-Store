package hac.ex4.services;

import hac.ex4.database.Product;
import hac.ex4.database.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void executePurchase(HashMap<Long, Product> cartProducts, HashMap<Long, Integer> cartQuantities){
        cartProducts.forEach((id, product) -> {
            productRepository.purchaseProduct(id, cartQuantities.get(id));
        });
    }
}
