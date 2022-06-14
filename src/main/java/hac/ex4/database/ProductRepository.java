package hac.ex4.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product Products);

    void delete(Product product);

    List<Product> findAll();

    Product findProductById(Long id);

    Product findProductByName(String name);

    List<Product> findFirst5ByOrderByDiscountDesc();
}
