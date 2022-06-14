package hac.ex4.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment save(Payment payment);
    List<Payment> findAll();

    List<Payment> findAllByOrderByDatetime();
}
