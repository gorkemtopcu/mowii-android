package com.example.mowii_frontend.api;
import com.example.mowii_frontend.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("user/create")
    Call<Void> registerUser(@Body User user);

    @POST("user/authenticate")
    Call<Void> authenticateUser(@Body User user);
}
