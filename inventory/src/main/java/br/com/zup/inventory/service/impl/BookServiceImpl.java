package br.com.zup.inventory.service.impl;

import br.com.zup.inventory.controller.request.BookRequest;
import br.com.zup.inventory.controller.request.UndoBookRequest;
import br.com.zup.inventory.entity.Book;
import br.com.zup.inventory.repository.BookRepository;
import br.com.zup.inventory.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public String book(BookRequest bookRequest) {
        System.out.println("ORDER: " + bookRequest.getOrderId() + " => BOOKING");

        return this.bookRepository.save(bookRequest.toEntity()).getId();
    }

    @Override
    public void undo(UndoBookRequest undoBookRequest) {
        System.out.println("ORDER: " + undoBookRequest.getOrderId() + " => UNDO BOOKING");

        Optional<Book> orderOptional = this.bookRepository.findOneByOrderId(undoBookRequest.getOrderId());
        orderOptional.ifPresent(book -> this.bookRepository.delete(book));
    }
}
