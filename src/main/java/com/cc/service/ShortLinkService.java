package com.cc.service;

public interface ShortLinkService {
    String createShortLink(String originalLink);
    String getOriginalLink(String shortLink);
}
