package com.spring.junit.exception.JunitExceptionMovieAssessment.service;

import com.spring.junit.exception.JunitExceptionMovieAssessment.model.Movie;
import com.spring.junit.exception.JunitExceptionMovieAssessment.repository.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class MovieServiceTest {

    // dummy data
    @Mock
    private MovieRepository repository;

    // dummy service for injecting data
    @InjectMocks
    private MovieServiceImpl service;


    // before each test case, ready the mocked data
    @Before
    public void setup(){

        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void getAllMovies(){
        List<Movie> movieList = new ArrayList<Movie>();
        movieList.add(new Movie(1,"Iron Man 1","Good","2005-05-05"));
        movieList.add(new Movie(2, "Iron Man 2", "Good", "2010-10-10"));
        movieList.add(new Movie(3, "Iron Man 3", "Very Good", "2015-03-03"));


        when(repository.findAll()).thenReturn(movieList);

        List<Movie> moviesResult = service.getMovies();

        assertEquals(3, moviesResult.size());
    }


    @Test
    public void getMovieById(){

        Movie movie = new Movie(1,"Iron Man 1","Good","2005-05-05");

        when(repository.findById(1L)).thenReturn(Optional.of(movie));

        Movie movieResult = service.getMovieById(1L);

        assertEquals(1, movieResult.getId());
        assertEquals("Iron Man 1", movieResult.getName());
        assertEquals("Good", movieResult.getRating());
    }


    @Test
    public void saveMovie(){

        Movie movie = new Movie(1,"Iron Man 1","Good","2005-05-05");

        when(repository.save(movie)).thenReturn(movie);

        Movie movieResult = service.saveMovie(movie);

        assertEquals(1, movieResult.getId());
        assertEquals("Iron Man 1", movieResult.getName());
        assertEquals("Good", movieResult.getRating());
    }

    @Test
    public void deleteMovieById(){

        Movie movie = new Movie(1,"Iron Man 1","Good","2005-05-05");

        service.removeMovie(movie.getId());

        verify(repository, times(1)).deleteById(movie.getId());

    }

    @Test
    public void deleteMovieById2(){
        List<Movie> movieList = new ArrayList<Movie>();
        movieList.add(new Movie(0,"Iron Man 1","Good","2005-05-05"));

        when(repository.findAll()).thenReturn(movieList);


        service.removeMovie(0L);
        movieList.remove(0);

        assertEquals(0, service.getMovies().size());



    }

}