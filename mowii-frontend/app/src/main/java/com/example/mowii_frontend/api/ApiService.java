package com.example.mowii_frontend.api;
import com.example.mowii_frontend.model.AddMovieToCollectionInput;
import com.example.mowii_frontend.model.Movie;
import com.example.mowii_frontend.model.MovieCollection;
import com.example.mowii_frontend.model.MovieCollectionCreationInput;
import com.example.mowii_frontend.model.MovieCollectionLikeInput;
import com.example.mowii_frontend.model.RemoveMovieFromCollectionInput;
import com.example.mowii_frontend.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @POST("user/create")
    Call<User> registerUser(@Body User user);
    @POST("user/authenticate")
    Call<User> authenticateUser(@Body User user);
    @GET("/movie-collection/all")
    Call<ArrayList<MovieCollection>> getAllMovieCollections();
    @GET("/movie-collection/user/{userId}")
    Call<ArrayList<MovieCollection>> getAllMovieCollectionsByUserId(@Path("userId") String userId);
    @GET("/movie/getAll")
    Call<ArrayList<Movie>> getAllMovies();
    @POST("/movie-collection/unlike")
    Call<MovieCollection> unlikeMovieCollection(@Body MovieCollectionLikeInput input);
    @POST("/movie-collection/like")
    Call<MovieCollection> likeMovieCollection(@Body MovieCollectionLikeInput input);
    @GET("/movie-collection/movies/{id}")
    Call<ArrayList<Movie>> getMoviesByCollectionId(@Path("id") String collectionId);
    @POST("/movie-collection/create")
    Call<MovieCollection> createMovieCollection(@Body MovieCollectionCreationInput input);
    @DELETE("/movie-collection/delete/{id}")
    Call<MovieCollection> deleteMovieCollection(@Path("id") String collectionId);
    @PUT("/movie-collection/add-movie")
    Call<Void> addMovieToCollections(@Body AddMovieToCollectionInput input);
    @PUT("/movie-collection/remove-movie")
    Call<Void> removeMovieFromCollection(@Body RemoveMovieFromCollectionInput input);
}
