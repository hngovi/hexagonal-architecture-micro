package com.example.ngovih.library.kafkaservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final KafkaTemplate<String, Object> template;
    private final String topicName;

    public KafkaController(final KafkaTemplate<String, Object> template,
                           @Value("${library.topic-name}") final String topicName) {
        this.template = template;
        this.topicName = topicName;
    }

    @GetMapping("/new-hot-book")
    public NewHotBookAdvice newHotBookAdvice(@RequestParam final String message,
                                             @RequestParam final String isbn) {
        final NewHotBookAdvice newHotBookAdvice = new NewHotBookAdvice(message, isbn);
        template.send(topicName, isbn, newHotBookAdvice);
        return newHotBookAdvice;
    }

}
