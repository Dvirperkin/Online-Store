package hac.ex4.beans;

import hac.ex4.database.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;

@Component
public class ShoppingCart implements Serializable {
    private ArrayList<Product> products;

    public ShoppingCart(){
        this.products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void add(Product product) {
        products.add(product);
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ShoppingCart getShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.add(new Product(5L, "Dvir", "Dvir", 5.0, 5.0));
        return shoppingCart;
    }
}