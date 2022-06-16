package hac.ex4.services;

import hac.ex4.database.Payment;
import hac.ex4.database.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment save(Payment payment){
        return paymentRepository.save(payment);
    }
    public List<Payment> findAll(){
        return paymentRepository.findAll();
    }
    public List<Payment> findAllByOrderByDatetime(){
        return paymentRepository.findAllByOrderByDatetime();
    }
}
