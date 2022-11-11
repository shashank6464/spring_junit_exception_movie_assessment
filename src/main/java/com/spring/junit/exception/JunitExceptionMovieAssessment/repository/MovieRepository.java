package com.spring.junit.exception.JunitExceptionMovieAssessment.repository;

import com.spring.junit.exception.JunitExceptionMovieAssessment.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("movieRepository")
public interface MovieRepository extends JpaRepository<Movie, Long> {

}