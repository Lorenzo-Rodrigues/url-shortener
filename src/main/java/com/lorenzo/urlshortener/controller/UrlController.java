package com.lorenzo.urlshortener.controller;

import com.lorenzo.urlshortener.controller.dto.ShortenUrlRequest;
import com.lorenzo.urlshortener.controller.dto.ShortenUrlResponse;
import com.lorenzo.urlshortener.entities.UrlEntity;
import com.lorenzo.urlshortener.repository.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class UrlController {
    private final UrlRepository urlRepository;

    public UrlController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @PostMapping("/shorten-url")
    public ResponseEntity<ShortenUrlResponse> shortenUrl (@RequestBody ShortenUrlRequest request,
                                            HttpServletRequest servletRequest){
        String id;
        do {
            RandomStringGenerator generator = new RandomStringGenerator.Builder()
                    .withinRange('A','Z')
                    .build();
            id = generator.generate(5);

        }while (urlRepository.existsById(id));

        urlRepository.save(new UrlEntity(id, request.url(), LocalDateTime.now().plusMinutes(1)));

        var redirectUrl = servletRequest.getRequestURL().toString().replace("shorten-url",id);

        return ResponseEntity.ok(new ShortenUrlResponse(redirectUrl));
    }
}
