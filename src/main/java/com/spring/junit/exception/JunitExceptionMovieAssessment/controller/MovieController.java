package com.spring.junit.exception.JunitExceptionMovieAssessment.controller;

import com.spring.junit.exception.JunitExceptionMovieAssessment.exceptionHandling.MovieException;
import com.spring.junit.exception.JunitExceptionMovieAssessment.model.Movie;
import com.spring.junit.exception.JunitExceptionMovieAssessment.model.PayloadValidation;
import com.spring.junit.exception.JunitExceptionMovieAssessment.model.Response;
import com.spring.junit.exception.JunitExceptionMovieAssessment.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService service;

    //get all movies
    @RequestMapping(value = "/get-movies", method = RequestMethod.GET)
    public ResponseEntity<List<Movie>> getMovies(){
        return new ResponseEntity<>(service.getMovies(), HttpStatus.OK);
    }

    //get movie by id
    @RequestMapping(value = "/get-movie-by-id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id) throws MovieException {
//        Employee emp = service.getEmployeeById(id);
//        if(emp==null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//
//        return new ResponseEntity<>(emp, HttpStatus.OK);

        Movie movie = service.getMovieById(id);
        if(movie==null || movie.getId()<=0){
            throw new MovieException("MOVIE DOESN'T EXISTS");
        }

        return new ResponseEntity<Movie>(service.getMovieById(id), HttpStatus.OK);

    }

    //save movie OR add a movie
    @RequestMapping(value = "/save-movie", method = RequestMethod.POST)
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie payload) throws MovieException{

        if(!PayloadValidation.createdPayloadValidation(payload)) throw new MovieException("PAYLOAD MALFORMED. ID MUST NOT BE DEFINED");

        return new ResponseEntity<Movie>(service.saveMovie(payload), HttpStatus.OK);
    }

    @RequestMapping(value = "/save-all-movie", method = RequestMethod.POST)
    public ResponseEntity<List<Movie>> saveAllMovies(@RequestBody List<Movie> movies) {
        return new ResponseEntity<>(service.saveAllMovies(movies), HttpStatus.OK);
    }

    //delete movie by id
    @RequestMapping(value = "/delete-movie/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> removeMovie(@PathVariable("id") Long id) throws MovieException{

        Movie movie = service.getMovieById(id);
        if(movie==null || movie.getId()<=0){
            throw new MovieException("MOVIE DOESN'T EXISTS");
        }

        service.removeMovie(id);
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "SUCCESSFULLY DELETED MOVIE"), HttpStatus.OK);
    }

    //update movie
    @RequestMapping(value = "/update-movie", method = RequestMethod.PATCH)
    public Movie updateMovie(@RequestBody Movie movie) throws MovieException{

        Movie movieRes = service.getMovieById(movie.getId());
        if(movieRes==null){
            throw new MovieException("MOVIE DOESN'T EXISTS");
        }

        return service.updateMovie(movie);
    }





}
