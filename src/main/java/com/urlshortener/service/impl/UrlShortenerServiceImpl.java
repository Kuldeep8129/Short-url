package com.urlshortener.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.urlshortener.service.UrlShortenerService;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

	Map<String , String> map = new HashMap<>();
	
	private static String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private int short_length = 9;	

	@Override
	public String shortenUrl(String originalUrl) {
		if(map.containsKey(originalUrl)) {
			return map.get(originalUrl);
		}else {
			String shortUrl = generateShortUrl();
			return shortUrl;
		}
	}

	private String generateShortUrl() {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for(int i=0 ; i<short_length;i++) {
			int index = random.nextInt(characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}

}
