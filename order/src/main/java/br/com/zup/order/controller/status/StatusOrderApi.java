package br.com.zup.order.controller.status;

import br.com.zup.order.controller.status.request.StatusOrderRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;


public interface StatusOrderApi {

        @ResponseStatus(HttpStatus.CREATED)
        @PostMapping(path = "/statusorder", consumes = MediaType.APPLICATION_JSON_VALUE)
        void atualizar(@RequestBody StatusOrderRequest request) throws Exception;

}
