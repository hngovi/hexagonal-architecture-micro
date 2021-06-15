package com.example.ngovih.library.bookservice.infrastructure.adapters;

import com.example.ngovih.library.bookservice.domain.model.Book;
import com.example.ngovih.library.bookservice.infrastructure.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookJpaAdapterTest {

    @TestConfiguration
    static class BookJpAAdapterTestContextConfiguration {
        @Bean
        public BookJpaAdapter bookJpaAdapter() {
            return new BookJpaAdapter();
        }
    }

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookJpaAdapter bookJpaAdapter;

    @Test
    public void fullTest() {
        Book book = new Book(null, "ISBN-1", "Title", "Author", "Description");
        Book savedBook = bookJpaAdapter.addBook(book);

        assertThat(savedBook.getIsbn()).isEqualTo(book.getIsbn());

        bookJpaAdapter.deleteBookById(savedBook.getId());

        assertThat(bookJpaAdapter.getBooks().size()).isEqualTo(0);

    }
}