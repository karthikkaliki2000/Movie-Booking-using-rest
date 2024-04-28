package com.example.MovieTicket.MovieBooking.communicator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;



@Service
public class RatingRestCommunicator {
	private RestTemplate restTemplate;

	@Autowired
	public RatingRestCommunicator(RestTemplateBuilder restTemplateBuilder) {
		// TODO Auto-generated constructor stub
		restTemplate = restTemplateBuilder.build();
	}

	public long getRating(String id) {
		String url = "http://localhost:8081/rating/id/" + id;
		// ResponseEntity<Long> response=restTemplate.getForEntity(url, Long.class);
		//Long ratingResponse = restTemplate.getForObject(url, Long.class);
		ResponseEntity<Long> ratingResponse = restTemplate.exchange(url, HttpMethod.GET,null,Long.class);
		// return response.getBody();
		return ratingResponse.getBody();
	}

	public void addRating(Map<String, Long> ratingmap) {

		String url = "http://localhost:8081/rating/add";
		// restTemplate.postForObject(url, ratingmap, Object.class);
		HttpEntity<Map<String, Long>> requestEntity = new HttpEntity<Map<String, Long>>(ratingmap);
		restTemplate.exchange(url, HttpMethod.POST, requestEntity, Object.class);
	}

	public void updateRating(Map<String, Long> updatedRating) {

		String url = "http://localhost:8081/rating/update";
		
		HttpEntity<Map<String, Long>> requestEntity = new HttpEntity<Map<String, Long>>(updatedRating);
		restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Object.class);
	}
	
	
	public void deleteRating(String id) {

		String url = "http://localhost:8081/rating/remove/id/"+id;
		restTemplate.exchange(url, HttpMethod.DELETE, null, Object.class);
	}
}
