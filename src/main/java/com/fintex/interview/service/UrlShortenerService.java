package com.fintex.interview.service;

import com.fintex.interview.dto.ShortenRequest;
import com.fintex.interview.dto.ShortenResponse;
import com.fintex.interview.model.ShortUrl;
import com.fintex.interview.repository.ShortUrlRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UrlShortenerService {

    private static final String BASE_URL = "http://localhost:8080/resolve/";

    private final ShortUrlRepository shortUrlRepository;

    public UrlShortenerService(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    public ShortenResponse shorten(ShortenRequest request) {
        String shortCode = UUID.randomUUID().toString().replace("-", "");

        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setOriginalUrl(request.getOriginalUrl());
        shortUrl.setShortCode(shortCode);

        shortUrlRepository.save(shortUrl);

        return new ShortenResponse(
                shortUrl.getOriginalUrl(),
                shortCode,
                BASE_URL + shortCode
        );
    }

    public Optional<String> resolve(String shortCode) {
        return shortUrlRepository.findByShortCode(shortCode)
                .map(ShortUrl::getOriginalUrl);
    }
}
