package com.example.MovieTicket.MovieBooking.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.MovieTicket.MovieBooking.Model.Movie;
import com.example.MovieTicket.MovieBooking.service.MovieServiceInterface;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ticket")
public class Controller {

	@Autowired
	MovieServiceInterface movieService;
	
	@GetMapping("/movies")
	public List<Movie> getAllMovies(){
		return movieService.getAllMovies();
	}
	
	@PostMapping("/movie")
	public void saveMovie(@Valid @RequestBody Movie movie,BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			throw new RuntimeException("Invalid movie body");
		}
		else {
			movieService.saveMovie(movie);
		}
		
	}
	public Controller() {
		// TODO Auto-generated constructor stub
	}
	@GetMapping("/movie/{id}")
	public Movie getMovieById(@PathVariable("id") String id){
		
		return movieService.getMovieById(id);
	}
	
	@DeleteMapping("/movie/{id}")
	public void deleteMovieById(@PathVariable("id") String id) {
		System.out.println("Delete controller"+id);
		movieService.deleteMovie(id);
	}
	
	@PutMapping("/update/{id}")
	public void updateMovieById(@Valid @RequestBody Movie topic,@PathVariable("id") String id){
	
		movieService.updateMovie(topic, id);
	}
	
	
}
