package com.fintex.interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class InterviewApplication {

    public static void main(String[] args) {
        // Europe/Kiev was renamed to Europe/Kyiv; PostgreSQL rejects the old name
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Kyiv"));
        SpringApplication.run(InterviewApplication.class, args);
    }
}
