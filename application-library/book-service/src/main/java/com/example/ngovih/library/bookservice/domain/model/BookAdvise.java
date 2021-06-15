package com.example.ngovih.library.bookservice.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookAdvise {
    private final String message;
    private final String isbn;

    public BookAdvise(@JsonProperty("message") final String message, @JsonProperty("isbn") final String isbn) {
        this.message = message;
        this.isbn = isbn;
    }
}
