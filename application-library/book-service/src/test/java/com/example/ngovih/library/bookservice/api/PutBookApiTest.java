package com.example.ngovih.library.bookservice.api;

import com.example.ngovih.library.bookservice.domain.model.Book;
import com.example.ngovih.library.bookservice.domain.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@WebFluxTest(controllers = BookController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
public class PutBookApiTest {

    @Autowired
    private WebTestClient client;

    // domain service
    @MockBean
    BookService bookService;

    @Test
    void put() {
        String endpoint = "/book/update";
        Book newFakeBook = new Book(1L, "", "", "", "");
        when(bookService.updateBook(newFakeBook)).thenReturn(Mono.just(newFakeBook));
        client.put().uri(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(newFakeBook), Book.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Book.class)
                .isEqualTo(newFakeBook);

    }
}
