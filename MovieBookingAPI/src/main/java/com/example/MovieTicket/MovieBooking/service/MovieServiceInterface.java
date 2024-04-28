package com.example.MovieTicket.MovieBooking.service;

import java.util.List;

import com.example.MovieTicket.MovieBooking.Model.Movie;

public interface MovieServiceInterface {
	public void saveMovie(Movie movie);
	public Movie getMovieById(String id);
	public List<Movie> getAllMovies();
	public void updateMovie(Movie movie,String id);
	public void deleteMovie(String id);
	
}
