package com.spring.junit.exception.JunitExceptionMovieAssessment.model;

public class PayloadValidation {

    public static boolean createdPayloadValidation(Movie movie){

        if(movie.getId()>0) return false;
        return true;
    }


}