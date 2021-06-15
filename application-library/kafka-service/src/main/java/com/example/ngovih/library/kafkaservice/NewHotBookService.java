package com.example.ngovih.library.kafkaservice;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;

@Service
public class NewHotBookService {
    private static final Logger logger = LoggerFactory.getLogger(NewHotBookService.class);

    @KafkaListener(topics = "new-hotbook-topic", clientIdPrefix = "string",
    containerFactory = "kafkaListenerStringContainerFactory")
    public void listenAsObject(ConsumerRecord<String, String> cr, @Payload String payload) {
        logger.info("Logger 1 [String] received key {}: Type [{}] | Payload: {} | Record: {}", cr.key()
        ,typeIdHeader(cr.headers()), payload, cr.toString());
    }

    private static String typeIdHeader(Headers headers) {
        return StreamSupport.stream(headers.spliterator(), false)
                .filter((header -> header.key().equals("__TypeId__")))
                .findFirst().map(header -> new String(header.value())).orElse("N/A");

    }
}
