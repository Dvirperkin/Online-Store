package hac.ex4.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Name is mandatory")
    private String name;

    private String image = "ProductImage.png";

    @NotNull(message = "Stock is mandatory")
    @PositiveOrZero(message = "Stock must be positive or zero")
    private Integer stock = 1;

    @PositiveOrZero(message = "Price must be positive or zero")
    private Double price;

    @Min(value = 0, message = "Discount must be between 0% to 100%")
    @Max(value = 100, message = "Discount must be between 0% to 100%")
    private Double discount;

    public Product(){}

    public Product(Long id , String name, String image, Double price, Double discount){
        this.id = id;
        this.name = name;
        if(image != null)
            this.image = image;
        this.price = price;
        this.discount = discount;
        this.stock = 1;
    }

    public Double getDiscount() {
        return discount;
    }
    public Double getPrice() {
        return price;
    }
    public Integer getStock() {
        return stock;
    }
    public Long getId() {
        return id;
    }
    public String getImage() {
        return image;
    }
    public String getName() {
        return name;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setStock(Integer stock) {
        if(stock < 0){
            throw new IllegalArgumentException("The requested product by the name: " + name + " with the id: " + id + " is out of stock.");
        }
        this.stock = stock;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setName(String name) {
        this.name = name;
    }
}
