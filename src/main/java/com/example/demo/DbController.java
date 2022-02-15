package com.example.demo;

import com.example.demo.configs.Database;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DbController {
    private final Database db;

    public DbController(Database db) {
        this.db = db;
    }

    @GetMapping("/db")
	public Database index() {
		return db;
	}
}
