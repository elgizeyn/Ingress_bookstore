package ingressunibank.bookstore.repositories;

import ingressunibank.bookstore.dto.BookDto;
import ingressunibank.bookstore.model.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findBookByBookName(String bookName);

    Optional<List<Book>> findBooksByPublisher(String publisherName);

    Optional<List<Book>> findBooksByPriceLessThan(double price, Pageable pageable);

    List<Book> findAll();

    BookDto save(BookDto bookDto);

}
