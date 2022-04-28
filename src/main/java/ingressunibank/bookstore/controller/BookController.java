package ingressunibank.bookstore.controller;


import ingressunibank.bookstore.dto.BookDto;
import ingressunibank.bookstore.model.Book;
import ingressunibank.bookstore.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books") // resource name
public class BookController {


    private final BookService bookService;


    @GetMapping("/all")
    public ResponseEntity<String> getBookList() {
        return new ResponseEntity<>("All books:" + bookService.bookList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{publisher}", method = RequestMethod.GET)
    public ResponseEntity<String> getPublisherBooks(@PathVariable("publisher") String publisherName) {
        return ResponseEntity.ok(publisherName + " books: " + bookService.getBooksByPublisher(publisherName));
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ResponseEntity<BookDto> getBookDetails(@RequestParam(name = "name") String bookName) {
        return ResponseEntity.ok(bookService.getBookByName(bookName));
    }

    @RequestMapping(value = "/paging/{page}", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> list(@RequestParam("price") double price, @PathVariable("page") int page) {
        return ResponseEntity.ok(bookService.getAllByPriceLessThan(price, Pageable.ofSize(page)));
    }

}
