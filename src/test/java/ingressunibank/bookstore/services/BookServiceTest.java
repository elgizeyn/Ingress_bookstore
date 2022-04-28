package ingressunibank.bookstore.services;


import ingressunibank.bookstore.dto.BookDto;
import ingressunibank.bookstore.dto.UpdateBookDto;
import ingressunibank.bookstore.model.Book;
import ingressunibank.bookstore.model.Customer;
import ingressunibank.bookstore.repositories.BookRepository;
import ingressunibank.bookstore.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RequiredArgsConstructor
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {


    @InjectMocks
    private BookImpl bookService;

    private List<Book> bookList = new ArrayList<>();
    private Customer publisher1;
    private Customer publisher2;
    private Customer user;
    private Book book1;
    private Book book2;
    private Book book3;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Before
    public void setUp() {
        publisher1 = Customer.builder()
                .email("publisher1@gmail.com")
                .name("publisher1")
                .password("1234")
                .roles("PUBLISHER")
                .personId(5)
                .build();
        publisher2 = Customer.builder()
                .email("publisher2@gmail.com")
                .name("4321")
                .password("publisher2")
                .roles("PUBLISHER")
                .personId(6)
                .build();
        user = Customer.builder()
                .email("user@gmail.com")
                .name("user")
                .password("user1234")
                .personId(7)
                .roles("USER")
                .build();

        book1 = Book.builder()
                .authorName("Ali Nesin")
                .price(50.0)
                .bookName("book1")
                .publisher(publisher1)
                .bookId(1)
                .build();
        book2 = Book.builder()
                .authorName("Huri Nesin")
                .price(150.0)
                .bookName("book2")
                .publisher(publisher2)
                .bookId(2)
                .build();
        book3 = Book.builder()
                .authorName("Auro")
                .price(150.0)
                .bookName("book3")
                .publisher(publisher2)
                .bookId(3)
                .build();

        bookList.add(book1);
        bookList.add(book2);
        Mockito.when(bookRepository.findAll()).thenReturn(bookList);
        Mockito.when(bookRepository.findBookByBookName("book1")).thenReturn(Optional.of(book1));
        Mockito.when(bookRepository.findBookByBookName("book2")).thenReturn(Optional.of(book2));
        Mockito.when(bookRepository.save(Book.builder().authorName("Auro").bookName("book2").price(150.0).publisher(publisher2).build())).thenReturn(book2);
        Mockito.when(bookRepository.findBookByBookName("BOOK1")).thenReturn(Optional.of(book1));
        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.of(book1));
        Mockito.when(bookRepository.findBooksByPublisher("publisher1")).thenReturn(Optional.of(Arrays.asList(book1)));
        Mockito.when(customerRepository.findByName("publisher1")).thenReturn(Optional.of(publisher1));
    }

    @Test
    public void returnBookList() {
        List<String> books = bookService.bookList();
        Assert.assertEquals(books, Arrays.asList("book1", "book2"));
    }

    @Test
    public void getBookByName() {
        BookDto dto = bookService.getBookByName("book1");
        BookDto bookDto = BookDto.builder().publisherName("publisher1").bookName("book1").price(50.0).authorName("Ali Nesin").build();
        Assert.assertEquals(dto, bookDto);
    }

    @Test
    public void getBooksByPublisher() {
        List<Book> books = bookService.getBooksByPublisher("publisher1");
        List<Book> books1 = new ArrayList<>();
        books1.add(book1);
        Assert.assertEquals(books, books1);
    }

    @Test
    public void addBook() {
        BookDto bookDto = BookDto.builder().bookName("boook1").authorName("Aurora").publisherName(publisher1.getName()).build();
        Mockito.when(bookRepository.save(bookDto)).thenReturn(bookDto);
        Assert.assertEquals(bookDto, bookService.addBook(bookDto));
    }


    @Test
    public void updateBook() {
        Customer customer = Customer.builder().roles("PUBLISHER").personId(1).email("publisher2@gmail.com").password("1233").name("publisher2")
                .build();
        Book book1 = Book.builder().bookName("book2").publisher(customer).build();
        HashSet<Book> books = new HashSet<>();
        books.add(book1);
        customer.setBooks(books);
        Mockito.when(customerRepository.findByName("publisher2")).thenReturn(Optional.of(customer));
        Mockito.when(bookRepository.findBookByBookName("book2")).thenReturn(Optional.ofNullable(book1));
        Book book = bookService.updateBook(UpdateBookDto.builder().oldBookName("book2").publisherName(customer.getName())
                .publisherPassword(customer.getPassword())
                .updatedBookName("BOOK2").updatedPrice(20.0).build());
        Assert.assertEquals("BOOK2", book.getBookName());
    }

}
