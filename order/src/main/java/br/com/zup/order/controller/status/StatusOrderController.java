package br.com.zup.order.controller.status;

import br.com.zup.order.controller.status.request.StatusOrderRequest;
import br.com.zup.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/orders")
public class StatusOrderController implements StatusOrderApi {

    private OrderService orderService;

    @Autowired
    public StatusOrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @Override
    public void atualizar(@RequestBody StatusOrderRequest request) throws Exception {
            this.orderService.updateStatusOrder(request);
    }
}
