package com.example.demo.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * To set the configuration, specify environment variables DB_HOST, DB_PORT, DB_PASSWORD
 */
@ConstructorBinding
@ConfigurationProperties("db")
public class Database {
    public final String host;
    public final String port;
    public final String password;

    public Database(String host, String port, String password) {
        this.host = host;
        this.port = port; 
        this.password = password;
    }
}
