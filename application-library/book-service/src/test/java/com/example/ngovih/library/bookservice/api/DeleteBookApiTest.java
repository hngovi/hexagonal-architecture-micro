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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = BookController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
public class DeleteBookApiTest {

    @Autowired
    private WebTestClient client;

    // domain service
    @MockBean
    BookService bookService;

    @Test
    void delete() {
        String endpoint = "/book/delete/1";
        when(bookService.deleteBookById(any())).thenReturn(Mono.empty());
        client.delete().uri(endpoint)
                .exchange()
                .expectStatus()
                .isOk();
    }
}
