package com.lorenzo.urlshortener.controller;

import com.lorenzo.urlshortener.controller.dto.ShortenUrlRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlController {
    @PostMapping("/shorten")
    public ResponseEntity<Void> shortenUrl (@RequestBody ShortenUrlRequest request){
        return ResponseEntity.ok(null);
    }
}
