package com.spring.junit.exception.JunitExceptionMovieAssessment.service;

import com.spring.junit.exception.JunitExceptionMovieAssessment.model.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getMovies();

    Movie getMovieById(Long id);

    Movie saveMovie(Movie movie);

    List<Movie> saveAllMovies(List<Movie> movies);

    void removeMovie(Long id);

}
