package br.com.zup.inventory.repository;

import br.com.zup.inventory.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> {

    Optional<Book> findOneByOrderId(String orderId);
}
