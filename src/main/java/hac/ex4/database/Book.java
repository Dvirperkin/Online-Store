package hac.ex4.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
public class Book implements Serializable {

    @Id
    private Long id;

    @NotEmpty(message = "Name is mandatory")
    private String name;

    private String image = "resources/bookImage.png";
    private Integer quantity = 0;

    //@NotEmpty(message = "Price is mandatory")
    private Double price;
    private Double discount;

    public Book(){
        System.out.println("Default");
    }

    public Book(Long id ,String name, String image, Double price, Double discount){
        this.id = id;
        this.name = name;
        if(image != null)
            this.image = image;
        this.price = price;
        this.discount = discount;
        this.quantity += 1;
    }

    public Double getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
