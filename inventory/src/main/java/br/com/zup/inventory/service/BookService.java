package br.com.zup.inventory.service;

import br.com.zup.inventory.controller.request.BookRequest;
import br.com.zup.inventory.controller.request.UndoBookRequest;

public interface BookService {

    String book(BookRequest bookRequest);

    void undo(UndoBookRequest undoBookRequest);
}
