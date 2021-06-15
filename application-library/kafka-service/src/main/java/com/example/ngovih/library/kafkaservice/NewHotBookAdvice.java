package com.example.ngovih.library.kafkaservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewHotBookAdvice {
    private final String message;
    private final String isbn;

    public NewHotBookAdvice(@JsonProperty("message") final String message, @JsonProperty("isbn") final String isbn) {
        this.message = message;
        this.isbn = isbn;
    }

}
