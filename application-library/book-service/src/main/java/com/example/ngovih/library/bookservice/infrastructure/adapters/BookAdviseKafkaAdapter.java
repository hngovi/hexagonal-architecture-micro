package com.example.ngovih.library.bookservice.infrastructure.adapters;

import com.example.ngovih.library.bookservice.domain.model.Book;
import com.example.ngovih.library.bookservice.domain.model.BookAdvise;
import com.example.ngovih.library.bookservice.domain.ports.api.BookAdvisePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookAdviseKafkaAdapter implements BookAdvisePort {

    @Autowired
    private KafkaTemplate<String, BookAdvise> bookAdviseKafkaTemplate;

    @Value("${library.topic-name}")
    private String topicName;

    @Override
    public void newBookAdvise(Book book) {
        final BookAdvise bookAdvise = new BookAdvise(book.getTitle(), book.getIsbn());
        bookAdviseKafkaTemplate.send(topicName, bookAdvise.getIsbn(), bookAdvise);
    }
}
