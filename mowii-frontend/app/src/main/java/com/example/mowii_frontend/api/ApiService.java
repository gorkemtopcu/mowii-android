package com.example.mowii_frontend.api;
import com.example.mowii_frontend.model.Collection;
import com.example.mowii_frontend.model.User;

import java.lang.reflect.Array;
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
    Call<ArrayList<Collection>> getAllMovieCollections();

    @GET("/movie-collection/{id}")
    Call<ArrayList<Collection>> getMovieCollectionsByUser(@Path("id") String id);
}
