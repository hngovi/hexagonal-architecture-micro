package com.example.ngovih.library.bookservice.domain.ports.api;

import com.example.ngovih.library.bookservice.domain.model.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BookServicePort {
    Mono<Book> addBook(Book book);
    Mono<Void> deleteBookById(Long id);
    Mono<Book> updateBook(Book book);
    Flux<Book> getAllBooks();
    Mono<Book> getBookById(Long id);
}
