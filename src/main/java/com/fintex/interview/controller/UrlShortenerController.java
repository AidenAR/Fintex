package com.fintex.interview.controller;

import com.fintex.interview.dto.ShortenRequest;
import com.fintex.interview.dto.ShortenResponse;
import com.fintex.interview.service.UrlShortenerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("/persist")
    public ResponseEntity<ShortenResponse> persist(@RequestBody ShortenRequest request) {
        ShortenResponse response = urlShortenerService.shorten(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/resolve/{shortUrl}")
    public ResponseEntity<String> resolve(@PathVariable String shortUrl) {
        Optional<String> originalUrl = urlShortenerService.resolve(shortUrl);
        return originalUrl
                .map(url -> ResponseEntity.ok(url))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/r/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {
        Optional<String> originalUrl = urlShortenerService.resolve(shortUrl);
        if (originalUrl.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, originalUrl.get());
        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }
}
