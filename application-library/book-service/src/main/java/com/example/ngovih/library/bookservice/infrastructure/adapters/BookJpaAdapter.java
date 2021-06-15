package com.example.ngovih.library.bookservice.infrastructure.adapters;

import com.example.ngovih.library.bookservice.domain.model.Book;
import com.example.ngovih.library.bookservice.domain.ports.spi.BookSpiPort;
import com.example.ngovih.library.bookservice.infrastructure.entity.BookEntity;
import com.example.ngovih.library.bookservice.infrastructure.mappers.BookMapper;
import com.example.ngovih.library.bookservice.infrastructure.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookJpaAdapter implements BookSpiPort {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {
        BookEntity bookEntity = BookMapper.INSTANCE.bookToBookEntity(book);
        BookEntity bookEntitySaved = bookRepository.save(bookEntity);
        return BookMapper.INSTANCE.bookEntityToBook(bookEntitySaved);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book updateBook(Book book) {
        return addBook(book);
    }

    @Override
    public List<Book> getBooks() {
        List<BookEntity> bookEntityList = bookRepository.findAll();
        return BookMapper.INSTANCE.bookEntityListToBookList(bookEntityList);
    }

    @Override
    public Book getBookById(Long id) {
        Optional<BookEntity> bookEntity = bookRepository.findById(id);
        if(bookEntity.isPresent()) {
            return BookMapper.INSTANCE.bookEntityToBook(bookEntity.get());
        }
        return null;
    }
}

