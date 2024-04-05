package com.urlshortener.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.urlshortener.service.UrlShortenerService;

@RestController
public class UrlShortenerController {
	
	@Autowired
	UrlShortenerService urlShortenerService;
	
	Map<String , String> urlMap = new HashMap<String , String>();
	
	@GetMapping(value = "/fuddi")
	public String letsBounce() {
		return "fuddi";
	}
	
	@PostMapping(value = "/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody String originalUrl) {
    	String shortUrl = null ;
    	try {
    		System.out.println("Entered in controller");
    		shortUrl = urlShortenerService.shortenUrl(originalUrl);
    		urlMap.put(shortUrl, originalUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return ResponseEntity.status(HttpStatus.CREATED).body(shortUrl);
    }
	
	
	@PostMapping(value = "/shortUrl")
	public ResponseEntity<String> redirectToOriginalUrl(@RequestBody String shortUrl){
		String originalUrl = urlMap.get(shortUrl);
		if(originalUrl != null) {
			return ResponseEntity.status(HttpStatus.FOUND)
				    .location(URI.create(originalUrl))
				    .build();
		}else {
			return new ResponseEntity<>("Url Not FOund" , HttpStatus.NOT_FOUND);

		}
	}

}
