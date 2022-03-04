package com.orderly;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplicationClass implements CommandLineRunner{

    private static final Logger log = LoggerFactory.getLogger(MainApplicationClass.class);

    @Value("${spring.mail.password}")
    private String password;

    public static void main(String[] args) {
        SpringApplication.run(MainApplicationClass.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    log.info("PASSWORD: {}", password);
    }
}