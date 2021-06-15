package com.example.ngovih.library.bookservice.domain.ports.spi;

import com.example.ngovih.library.bookservice.domain.model.Book;

import java.util.List;

public interface BookSpiPort {
    Book addBook(Book book);
    void deleteBookById(Long id);
    Book updateBook(Book book);
    List<Book> getBooks();
    Book getBookById (Long id);
}
