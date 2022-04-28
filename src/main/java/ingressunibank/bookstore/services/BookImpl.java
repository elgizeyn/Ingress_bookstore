package ingressunibank.bookstore.services;

import ingressunibank.bookstore.dto.BookDto;
import ingressunibank.bookstore.dto.UpdateBookDto;
import ingressunibank.bookstore.exception.NoBookFoundException;
import ingressunibank.bookstore.exception.NoCustomerFoundException;
import ingressunibank.bookstore.model.Book;
import ingressunibank.bookstore.repositories.BookRepository;
import ingressunibank.bookstore.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookImpl implements BookService {


    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;


    @Override
    public List<String> bookList() {
        return bookRepository.findAll().stream().map(new Book()::getBookName).collect(Collectors.toList());
    }

    @Override
    public BookDto addBook(BookDto bookDto) {
        if (!customerRepository.findByName(bookDto.getPublisherName()).isPresent())
            throw new NoCustomerFoundException("No Publisher in DB with the name " + bookDto.getPublisherName());
        bookRepository.save(convertDto(bookDto));
        return bookDto;
    }

    @Override
    public BookDto getBookByName(String bookName) {
        var dto = new BookDto();
        var optionalBook = bookRepository.findBookByBookName(bookName);
        if (optionalBook.isPresent()) {
            var book = optionalBook.get();
            dto.setPrice(book.getPrice());
            dto.setAuthorName(book.getAuthorName());
            dto.setBookName(book.getBookName());
            dto.setPublisherName(book.getPublisher().getName());
            return dto;
        }
        throw new NoBookFoundException("No book in DB with given name: " + bookName);
    }


    @Override
    public List<Book> getBooksByPublisher(String publisherName) {
        var booksByPublisher = bookRepository.findBooksByPublisher(publisherName);
        if (!booksByPublisher.isPresent()) {
            throw new NoBookFoundException("No book is published by " + publisherName);
        }
        return booksByPublisher.get();
    }

    @Override
    public List<Book> getAllByPriceLessThan(double price, Pageable pageable) {
        var optionalBookList = bookRepository.findBooksByPriceLessThan(price, pageable);
        if (!optionalBookList.isPresent()) {
            throw new NoBookFoundException("No book with price less than " + price);
        }
        return optionalBookList.get();
    }

    @Override
    public Book updateBook(UpdateBookDto dto) {
        var publisher = customerRepository.findByName(dto.getPublisherName());
        var book = bookRepository.findBookByBookName(dto.getOldBookName());
        if (publisher.isPresent() && book.isPresent() && (publisher.get().getPassword().equals(dto.getPublisherPassword()))) {
            if (!(publisher.get().getBooks().contains(book.get()))) {
                throw new NoBookFoundException("No update for the book: " + dto.getOldBookName());
            }
            book.get().setBookName(dto.getUpdatedBookName());
            book.get().setPrice(dto.getUpdatedPrice());
            bookRepository.save(book.get());
            return book.get();
        } else
            throw new NoBookFoundException("No book is published by " + dto.getPublisherName());
    }

    @Override
    public Book getById(Integer id) {
        if (bookRepository.findById(id).isPresent())
            throw new NoBookFoundException("No book with given id: " + id);
        return bookRepository.findById(id).get();
    }

    private Book convertDto(BookDto bookDto) {
        return Book.builder()
                .bookName(bookDto.getBookName())
                .price(bookDto.getPrice())
                .publisher(customerRepository.findByName(bookDto.getPublisherName()).get())
                .authorName(bookDto.getAuthorName())
                .build();
    }

}
