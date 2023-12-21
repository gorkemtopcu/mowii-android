package com.example.mowii_frontend.api;
import com.example.mowii_frontend.model.Movie;
import com.example.mowii_frontend.model.MovieCollection;
import com.example.mowii_frontend.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("user/create")
    Call<Void> registerUser(@Body User user);

    @POST("user/authenticate")
    Call<Void> authenticateUser(@Body User user);

    @GET("/movie-collection/all")
    Call<ArrayList<MovieCollection>> getAllMovieCollections();

    @GET("/movie-collection/{id}")
    Call<ArrayList<MovieCollection>> getMovieCollectionsByUser(@Path("id") String id);

    @GET("/movie/getAll")
    Call<ArrayList<Movie>> getAllMovies();
}
