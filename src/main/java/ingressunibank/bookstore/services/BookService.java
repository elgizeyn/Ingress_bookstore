package ingressunibank.bookstore.services;

import ingressunibank.bookstore.dto.BookDto;
import ingressunibank.bookstore.dto.UpdateBookDto;
import ingressunibank.bookstore.model.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BookService {

    List<String> bookList();

    BookDto addBook(BookDto bookDto);

    BookDto getBookByName(String bookName);

    List<Book> getBooksByPublisher(String publisherName);

    List<Book> getAllByPriceLessThan(double price, Pageable pageable);

    Book updateBook(UpdateBookDto dto);

    Book getById(Integer id);

}
