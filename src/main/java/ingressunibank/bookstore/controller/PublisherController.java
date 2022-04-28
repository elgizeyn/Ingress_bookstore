package ingressunibank.bookstore.controller;

import ingressunibank.bookstore.dto.BookDto;
import ingressunibank.bookstore.dto.UpdateBookDto;
import ingressunibank.bookstore.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publisher")
public class PublisherController {

    private final BookService bookService;

    @PostMapping("/newBook")
    public ResponseEntity<String> addBook(@RequestBody BookDto bookDto) {
        bookService.addBook(bookDto);
        return ResponseEntity.ok(bookDto.getBookName() + " is published by " + bookDto.getPublisherName());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateBook(@RequestBody UpdateBookDto dto) {
        bookService.updateBook(dto);
        return ResponseEntity.ok(dto.getOldBookName() + " book credentials are modified");
    }

}
