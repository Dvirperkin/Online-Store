package hac.ex4.database;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @DecimalMin("1")
    private Double amount;

    @CreationTimestamp
    private Date datetime;

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
