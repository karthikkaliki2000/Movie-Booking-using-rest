package com.example.MovieTicket.MovieBooking.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.MovieTicket.MovieBooking.Exceptions.IdAlreadyExist;
import com.example.MovieTicket.MovieBooking.Exceptions.IdNotFound;
import com.example.MovieTicket.MovieBooking.Model.Movie;
import com.example.MovieTicket.MovieBooking.communicator.RatingRestCommunicator;

@Service
public class MovieService implements MovieServiceInterface {
	List<Movie> movieList=new ArrayList<Movie>();
	Map<String,Movie> movieMap=new HashMap<String, Movie>();
	
	@Autowired
	RatingRestCommunicator ratingRestCommunicator;
	
	@Override
	public void saveMovie(Movie movie) {
		// TODO Auto-generated method stub
		
		if(ObjectUtils.isEmpty(movieMap.get(movie.getId()))) {
			HashMap<String, Long> ratingMap=new HashMap<String, Long>();
			ratingMap.put(movie.getId(), movie.getMovieRating());
			ratingRestCommunicator.addRating(ratingMap);
			movieList.add(movie);
			movieMap.put(movie.getId(), movie);
		}
		else {
			throw new IdAlreadyExist("Id already Exists");
		}
		
		
	}
	@Override
	public Movie getMovieById(String id) {
		// TODO Auto-generated method stub
		if(ObjectUtils.isEmpty(movieMap.get(id))) {
			throw new IdNotFound("Id not found");
		}
		else {
			Movie movie=movieMap.get(id);
			long updatedrating=ratingRestCommunicator.getRating(id);
			movie.setMovieRating(updatedrating);
			return movieMap.get(id);
		}
		
		
	}
	@Override
	public List<Movie> getAllMovies() {
		// TODO Auto-generated method stub
		return movieList;
	}
	@Override
	public void updateMovie(Movie newmovie, String id) {
		// TODO Auto-generated method stub
		if(ObjectUtils.isEmpty(movieMap.get(id))) {
			throw new IdNotFound("Id not found");
		}
		else {
			Movie existingMovie=movieMap.get(id);
			movieList.remove(existingMovie);
			HashMap<String, Long> ratingMap=new HashMap<String, Long>();
			ratingMap.put(newmovie.getId(), newmovie.getMovieRating());
			ratingRestCommunicator.updateRating(ratingMap);
			movieList.add(newmovie);
			movieMap.put(id, newmovie);
		}
		
	}
	@Override
	public void deleteMovie(String id) {
		// TODO Auto-generated method stub
		if(movieMap.get(id)==null) {
			
			throw new IdNotFound("Id not found");
		}
		else {
			
			Movie existingMovie=movieMap.get(id);
			ratingRestCommunicator.deleteRating(id);
			movieList.remove(existingMovie);
			movieMap.remove(id, existingMovie);
		}
		
	}
	
	
}
