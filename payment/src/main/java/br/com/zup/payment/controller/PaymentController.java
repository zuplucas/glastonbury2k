package br.com.zup.payment.controller;

import br.com.zup.payment.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController implements PaymentApi {

    PaymentService paymentService;


    @Autowired
    public void PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;

    }

    @Override
    public boolean payment() throws Exception {
        return paymentService.validPayment();
    }
}
