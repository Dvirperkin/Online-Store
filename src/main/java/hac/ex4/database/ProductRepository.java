package hac.ex4.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product Products);

    void delete(Product product);

    List<Product> findAll();

    Product findProductById(Long id);

    Product findProductByName(String name);

    List<Product> findFirst5ByOrderByDiscountDesc();

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Product u SET u.stock = u.stock-:reduce WHERE u.id = :id")
    void purchaseProduct(@Param("id") Long id, @Param("reduce") Integer reduce);

}