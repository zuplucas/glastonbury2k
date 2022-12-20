package br.com.zup.inventory.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.inventory.controller.request.BookRequest;

@RestController
@RequestMapping("/inventory")
public class InventoryController implements InventoryApi {

	@Autowired
	public InventoryController() {

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/booking", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Boolean book(@RequestBody BookRequest request) {
		System.out.println("Inventory Booking book");
		System.out.println(request.getOrderEntries());

		Random r = new Random();

		if (r.nextInt(2) % 2 == 0) {
			System.out.println("SUCESS");
			System.out.println(request);
			return Boolean.TRUE;
		} else {
			System.out.println("ERROR");
			System.out.println(request);
			System.out.println("THROWS BPMN Error - InventoryApiError");
			return Boolean.FALSE;
		}
	}
}
