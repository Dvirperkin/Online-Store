package hac.ex4.beans;

import hac.ex4.database.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.util.HashMap;

@Component
public class ShoppingCart implements Serializable {

    private HashMap<Long, Product> products;

    public ShoppingCart(){
        this.products = new HashMap<>();
    }

    public HashMap<Long, Product> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Long, Product> products) {
        this.products = products;
    }

    public void add(Product product) {

        if(products.containsKey(product.getId())){
            Product updatedProduct = products.get(product.getId());
            updatedProduct.setQuantity(updatedProduct.getQuantity() + 1);
            products.replace(product.getId(), updatedProduct);
            return;
        }

        products.put(product.getId(), product);
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ShoppingCart getShoppingCart() {
        return new ShoppingCart();
    }
}