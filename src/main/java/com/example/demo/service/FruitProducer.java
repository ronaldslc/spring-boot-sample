package com.example.demo.service;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * The fruit producer service can be used by any controller that may want to produce fruits
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class FruitProducer {

    private static final String TOPIC = "fruits";

    private final KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String message) {
        ListenableFuture<SendResult<String, String>> fut = kafkaTemplate.send(TOPIC, message);
        fut.addCallback((result) -> {
            RecordMetadata md = result.getRecordMetadata();
            log.info(String.format("#### -> Sent fruit to -> partition: %s, offset: %s", md.partition(), md.offset()));
        }, (err) -> {
            log.error("Unable to send fruit", err);
        });
    }
}
