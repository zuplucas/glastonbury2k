package br.com.zup.payment.services.impl;

import br.com.zup.payment.services.PaymentService;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class PaymentServicesImpl implements PaymentService {


    @Override
    public boolean validPayment() {

         Random payment = new Random();
         if (payment.nextInt(10) >= 5){
            return true;
         } else {
             return false;
         }
    }
}
