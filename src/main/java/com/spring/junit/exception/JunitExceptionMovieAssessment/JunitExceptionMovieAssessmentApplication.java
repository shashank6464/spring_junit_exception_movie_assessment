package com.spring.junit.exception.JunitExceptionMovieAssessment;

import com.spring.junit.exception.JunitExceptionMovieAssessment.model.Movie;
import com.spring.junit.exception.JunitExceptionMovieAssessment.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JunitExceptionMovieAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(JunitExceptionMovieAssessmentApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(MovieRepository repository){
		return args -> {
			repository.save(new Movie(1,"Iron Man 1","Good","2005-05-05"));
			repository.save(new Movie(2, "Iron Man 2", "Good", "2010-10-10"));
			repository.save(new Movie(3, "Iron Man 3", "Very Good", "2015-03-03"));
		};
	}

}
