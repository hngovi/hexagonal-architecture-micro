package com.example.ngovih.library.bookservice.domain.service;

import com.example.ngovih.library.bookservice.domain.errors.BookNotFound;
import com.example.ngovih.library.bookservice.domain.model.Book;
import com.example.ngovih.library.bookservice.domain.ports.api.BookAdvisePort;
import com.example.ngovih.library.bookservice.domain.ports.api.BookServicePort;
import com.example.ngovih.library.bookservice.domain.ports.spi.BookSpiPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService implements BookServicePort {

    @Autowired
    BookSpiPort bookSpiPort;

    @Autowired
    BookAdvisePort bookAdvisePort;

    public Flux<Book> getAllBooks() {
        return Flux.fromIterable(bookSpiPort.getBooks());
    }

    public Mono<Book> getBookById(Long id) {
        final Book book = bookSpiPort.getBookById(id);
        if(book != null) return Mono.just(book);
        return Mono.error(new BookNotFound());
    }

    public Mono<Book> addBook(Book book) {
        Book addedBook = bookSpiPort.addBook(book);
        bookAdvisePort.newBookAdvise(addedBook);
        return Mono.just(bookSpiPort.addBook(addedBook));
    }

    public Mono<Book> updateBook(Book book) {
        return Mono.just(bookSpiPort.updateBook(book));
    }

    public Mono<Void> deleteBookById(Long id) {
        final Book book = bookSpiPort.getBookById(id);

        if(book == null) {
            return Mono.error(new BookNotFound());
        }

        bookSpiPort.deleteBookById(book.getId());
        return Mono.empty();
    }
}
