package com.spring.junit.exception.JunitExceptionMovieAssessment.controller;

import com.spring.junit.exception.JunitExceptionMovieAssessment.JunitExceptionMovieAssessmentApplication;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)   // for running this with junit4
@ContextConfiguration(classes = JunitExceptionMovieAssessmentApplication.class)  // context setting for the real data (added in main)
@SpringBootTest // spring test
@FixMethodOrder(MethodSorters.NAME_ASCENDING)  // to execute the test methods in order (based on name)
public class MovieControllerTest {

    // For controller based mocks (for web layer)
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext movieContext; // autowired the configuration


    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(movieContext).build();

    }

    // THESE test cases check the payload details after the controller URI is called;


    @Test
    public void verifyAllMovies() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/movie/get-movies")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(print());
    }


    @Test
    public void verifyGetById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/movie/get-movie-by-id/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.rating").exists())
                .andExpect(jsonPath("$.id").value(2))  // checking the expected value and expression value(output response)
                .andExpect(jsonPath("$.name").value("Iron Man 2"))
                .andDo(print());
    }


    // EXCEPTION CHECKING
    // bad request when method used should be post instead of get -> exception successfull
    @Test
    public void verifyValidMovie() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/movie/save-movie")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("THE REQUEST CANNOT BE PLACED DUE TO MALFUNCTION SYNTAX"))
                .andDo(print());
    }

    // EXCEPTION CHECKING (INVALID ID)
    @Test
    public void verifyInvalidGetById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/movie/get-movie-by-id/5")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("MOVIE DOESN'T EXISTS"))
                .andDo(print());
    }

















}