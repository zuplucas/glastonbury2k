package br.com.zup.order.orchestrator.integration.order;

import br.com.zup.order.orchestrator.integration.order.request.BookedOrderRequest;
import br.com.zup.order.orchestrator.integration.order.request.FinishOrderRequest;
import br.com.zup.order.orchestrator.integration.order.request.RejectOrderRequest;
import br.com.zup.order.orchestrator.integration.order.request.RejectPaymentOrderRequest;
import feign.Headers;
import feign.RequestLine;

public interface OrderApi {

    @RequestLine("POST /reject-order")
    @Headers("Content-Type: application/json")
    void rejectOrder(RejectOrderRequest request);

    @RequestLine("POST /finish-order")
    @Headers("Content-Type: application/json")
    void finishOrder(FinishOrderRequest request);

    @RequestLine("POST /booked")
    @Headers("Content-Type: application/json")
    void bookedOrder(BookedOrderRequest request);

    @RequestLine("POST /reject-payment-order")
    @Headers("Content-Type: application/json")
    void    rejectPaymentOrder(RejectPaymentOrderRequest request);

}
