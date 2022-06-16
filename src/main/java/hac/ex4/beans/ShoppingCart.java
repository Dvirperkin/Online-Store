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

    private HashMap<Long, Integer> quantities;
    private HashMap<Long, Product> products;
    private double totalPrice;

    public ShoppingCart(){
        this.totalPrice = 0;
        this.products = new HashMap<>();
        this.quantities = new HashMap<>();
    }

    public HashMap<Long, Product> getProducts() {
        return products;
    }
    public HashMap<Long, Integer> getQuantities() {
        return quantities;
    }
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setProducts(HashMap<Long, Product> products) {
        this.products = products;
    }
    public void setQuantities(HashMap<Long, Integer> quantities) {
        this.quantities = quantities;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void add(Product product) {

        totalPrice += product.getPrice() * (1 - product.getDiscount() / 100);

        if(products.containsKey(product.getId())){
            quantities.replace(product.getId(), quantities.get(product.getId()) + 1);
            return;
        }

        quantities.put(product.getId(), 1);
        products.put(product.getId(), product);
    }
    public void delete(Long id) {
        totalPrice -= products.get(id).getPrice() * (1 - products.get(id).getDiscount() / 100);
        products.remove(id);
        quantities.remove(id);
    }
    public void clear() {
        totalPrice = 0;
        products.clear();
        quantities.clear();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ShoppingCart getShoppingCart() {
        return new ShoppingCart();
    }
}