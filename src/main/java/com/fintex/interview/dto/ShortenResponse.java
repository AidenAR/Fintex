package com.fintex.interview.dto;

public class ShortenResponse {

    private String originalUrl;
    private String shortCode;
    private String shortenedUrl;

    public ShortenResponse(String originalUrl, String shortCode, String shortenedUrl) {
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
        this.shortenedUrl = shortenedUrl;
    }

    public String getOriginalUrl() { return originalUrl; }
    public String getShortCode() { return shortCode; }
    public String getShortenedUrl() { return shortenedUrl; }
}
