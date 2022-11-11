package com.spring.junit.exception.JunitExceptionMovieAssessment.util;

import com.spring.junit.exception.JunitExceptionMovieAssessment.model.Movie;
import com.spring.junit.exception.JunitExceptionMovieAssessment.model.PayloadValidation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PayloadValidatorTest {


    @Test
    public void validatePayload(){

        Movie movie = new Movie(1,"Iron Man 1","Good","2005-05-05");

        assertEquals(false, PayloadValidation.createdPayloadValidation(movie));

    }

    @Test
    public void validateInvalidPayload(){
        Movie movie = new Movie(0,"Iron Man 1","Good","2005-05-05");

        assertEquals(true, PayloadValidation.createdPayloadValidation(movie));

    }


}
