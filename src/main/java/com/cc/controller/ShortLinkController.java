package com.cc.controller;

import com.cc.service.ShortLinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/u")
public class ShortLinkController {
    private final ShortLinkService shortLinkService;

    public ShortLinkController(ShortLinkService shortLinkService) {
        this.shortLinkService = shortLinkService;
    }

    @PostMapping
    public ResponseEntity<String> createShortLink(@RequestBody String originalLink) {
        String shortLink = shortLinkService.createShortLink(originalLink);
        return ResponseEntity.ok("http://localhost:8080/u/"+shortLink);
    }

    @GetMapping("/{shortLink}")
    public ResponseEntity<Void> redirectToOriginalLink(@PathVariable String shortLink) {
        String originalLink = shortLinkService.getOriginalLink(shortLink);
        if (originalLink != null) {
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                    .location(URI.create(originalLink))
                    .build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}