package com.example.mowii_frontend.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mowii_frontend.api.ApiService;
import com.example.mowii_frontend.api.RetrofitClient;
import com.example.mowii_frontend.api.ApiResponse;
import com.example.mowii_frontend.model.Movie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {
    private final MutableLiveData<ApiResponse<ArrayList<Movie>>> allMoviesResult = new MutableLiveData<>();
    private final ApiService apiService = RetrofitClient.createService(ApiService.class);

    public LiveData<ApiResponse<ArrayList<Movie>>> getAllMoviesResult() {
        return allMoviesResult;
    }

    public void getAllMovies() {
        Call<ArrayList<Movie>> call = apiService.getAllMovies();
        call.enqueue(new Callback<ArrayList<Movie>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Movie>> call, @NonNull Response<ArrayList<Movie>> response) {
                if (response.isSuccessful()) {
                    allMoviesResult.setValue(new ApiResponse<ArrayList<Movie>>(true, "", response.body()));
                } else {
                    allMoviesResult.setValue(new ApiResponse<ArrayList<Movie>>(false, "Something went wrong.", null));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Movie>> call, @NonNull Throwable t) {
                allMoviesResult.setValue(new ApiResponse<ArrayList<Movie>>(false, "Something went wrong.", null));
            }
        });
    }
}
