package com.spring.junit.exception.JunitExceptionMovieAssessment.service;

import com.spring.junit.exception.JunitExceptionMovieAssessment.model.Movie;
import com.spring.junit.exception.JunitExceptionMovieAssessment.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service("movieService")
public class MovieServiceImpl implements  MovieService{

    @Autowired
    private MovieRepository repository;


    @Override
    public List<Movie> getMovies() {
        return repository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) {
        Optional<Movie> mov = repository.findById(id);
        if(mov.isPresent()) return mov.get();
        return null;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return repository.save(movie);
    }

    @Override
    public List<Movie> saveAllMovies(List<Movie> movies) {
        return repository.saveAll(movies);
    }

    @Override
    public void removeMovie(Long id) {
//        if(repository.existsById(id)) { repository.deleteById(id); return "Delete Successfully!"; }
//        return "Employee NOT FOUND";
        repository.deleteById(id);
    }

    public Movie updateMovie(Movie movie){
        return repository.save(movie);
//        return "Movie updated successfully";
    }

}