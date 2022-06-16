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
    public int getCartSize(){return products.size();}
    public void setProducts(HashMap<Long, Product> products) {
        this.products = products;
    }
    public void setQuantities(HashMap<Long, Integer> quantities) {
        this.quantities = quantities;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Adds product to shopping cart.
     * @param product - The product to add.
     * @param quantity - The quantity of this product that we want to add.
     */
    public void add(Product product, Integer quantity) {

        totalPrice += (product.getPrice() * (1 - product.getDiscount() / 100)) * quantity;

        if(products.containsKey(product.getId())){
            quantities.replace(product.getId(), quantities.get(product.getId()) + quantity);
            return;
        }

        quantities.put(product.getId(), quantity);
        products.put(product.getId(), product);
    }

    /**
     * Delete product from the cart
     * @param id - The id of the product.
     */
    public void delete(Long id) {
        totalPrice -= products.get(id).getPrice() * (1 - products.get(id).getDiscount() / 100);
        products.remove(id);
        quantities.remove(id);
    }

    /**
     * Clears the shopping cart.
     */
    public void clear() {
        totalPrice = 0;
        products.clear();
        quantities.clear();
    }

    /**
     *
     * @return
     */
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ShoppingCart getShoppingCart() {
        return new ShoppingCart();
    }
}