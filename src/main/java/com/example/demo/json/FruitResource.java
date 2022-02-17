package com.example.demo.json;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PreDestroy;

import com.example.demo.service.FruitProducer;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/fruits")
@Slf4j
public class FruitResource {
    
    private Set<Fruit> fruits = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
    private final FruitProducer producer;

    public FruitResource(FruitProducer producer) {
        this.producer = producer;
    }

    @KafkaListener(topics = "fruits")
    public void consume(@Payload String message, 
    @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) throws IOException {
        log.info(String.format("#### -> Consumed fruit -> %s from -> partition: %s", message, partition));
        fruits.add(new Fruit(message, ""));
    }
    
    @GetMapping
    public Set<Fruit> list() {
        return fruits;
    }

    @PostMapping
    public void add(@RequestBody Fruit fruit) {
        this.producer.sendMessage(fruit.name);
    }

    @DeleteMapping
    public Set<Fruit> delete(@RequestBody Fruit fruit) {
        fruits.removeIf(existingFruit -> existingFruit.name.contentEquals(fruit.name));
        return fruits;
    }
}
