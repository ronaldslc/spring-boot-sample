package com.example.demo.json;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fruits")
public class FruitResource {
    
    private Set<Fruit> fruits = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public FruitResource() {
        fruits.add(new Fruit("Apple", "Winter fruit"));
        fruits.add(new Fruit("Pineapple", "Tropical fruit"));
    }

    @GetMapping
    public Set<Fruit> list() {
        return fruits;
    }

    @PostMapping
    public Set<Fruit> add(@RequestBody Fruit fruit) {
        fruits.add(fruit);
        return fruits;
    }

    @DeleteMapping
    public Set<Fruit> delete(@RequestBody Fruit fruit) {
        fruits.removeIf(existingFruit -> existingFruit.name.contentEquals(fruit.name));
        return fruits;
    }
}
