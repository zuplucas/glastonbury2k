package br.com.zup.inventory.service.book;

import br.com.zup.inventory.controller.request.BookRequest;

public interface BookService {

    boolean book(BookRequest request);
}
