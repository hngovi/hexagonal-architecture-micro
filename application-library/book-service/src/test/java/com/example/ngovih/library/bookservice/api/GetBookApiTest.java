package com.example.ngovih.library.bookservice.api;

import com.example.ngovih.library.bookservice.domain.model.Book;
import com.example.ngovih.library.bookservice.domain.service.BookService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@WebFluxTest(controllers = BookController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
public class GetBookApiTest {

    private static Map<Long, Book> allBooks;

    static {
        allBooks = new HashMap<>();
        allBooks.put(1L, new Book(1L, "ISBN-1", "Title 1", "Author 1", "Book 1"));
        allBooks.put(2L, new Book(2L,"ISBN-2", "Title 2", "Author 2", "Book 2"));
    }

    @Autowired
    private WebTestClient client;

    // domain service
    @MockBean
    BookService bookService;


    @Test
    void get() {
        String endpoint = "/book/get";
        List<Book> bookList = new ArrayList<>(allBooks.values());
        Flux<Book> bookFlux = Flux.fromIterable(allBooks.values());
        when(bookService.getAllBooks()).thenReturn(bookFlux);
        getOk(endpoint)
                .expectBodyList(Book.class)
                .hasSize(2)
                .isEqualTo(bookList);
    }

    @Test
    void getById() {
        String endpoint = "/book/get/1";
        when(bookService.getBookById(1L)).thenReturn(Mono.just(allBooks.get(1L)));
        getOk(endpoint)
                .expectBody(Book.class)
                .isEqualTo(allBooks.get(1L));
    }

    @NotNull
    private WebTestClient.ResponseSpec getOk(String endpoint) {
        return client.get().uri(endpoint)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }
}
