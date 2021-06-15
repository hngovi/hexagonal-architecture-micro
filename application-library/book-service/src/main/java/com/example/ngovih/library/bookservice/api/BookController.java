package com.example.ngovih.library.bookservice.api;

import com.example.ngovih.library.bookservice.domain.errors.BookNotFound;
import com.example.ngovih.library.bookservice.domain.model.Book;
import com.example.ngovih.library.bookservice.domain.ports.api.BookServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookServicePort bookService;

    @GetMapping("/get")
    public Flux<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/get/{id}")
    public Mono<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/add")
    public Mono<Book> addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/update")
    public Mono<Book> updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteBookById(@PathVariable Long id) {
        return bookService.deleteBookById(id);
    }

    @ResponseStatus(
            value = HttpStatus.NOT_FOUND,
            reason = "Not Found"
    )
    @ExceptionHandler(BookNotFound.class)
    public void exceptionHandler() {}
}
