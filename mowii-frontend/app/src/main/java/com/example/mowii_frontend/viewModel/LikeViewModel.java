package com.example.mowii_frontend.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mowii_frontend.api.ApiService;
import com.example.mowii_frontend.api.RetrofitClient;
import com.example.mowii_frontend.api.ApiResponse;
import com.example.mowii_frontend.model.MovieCollection;
import com.example.mowii_frontend.model.MovieCollectionLikeInput;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LikeViewModel extends ViewModel {
    private final MutableLiveData<ApiResponse<Void>> likeResult = new MutableLiveData<>();

    public LiveData<ApiResponse<Void>> getLikeResult() {
        return likeResult;
    }

    private final ApiService apiService = RetrofitClient.createService(ApiService.class);

    public void likeMovieCollection(MovieCollectionLikeInput input) {
        Call<MovieCollection> call = apiService.likeMovieCollection(input);

        call.enqueue(new Callback<MovieCollection>() {
            @Override
            public void onResponse(@NonNull Call<MovieCollection> call, @NonNull Response<MovieCollection> response) {
                if (response.code() == 200) {
                    likeResult.setValue(new ApiResponse<>(true, "Like successful", null));
                } else {
                    likeResult.setValue(new ApiResponse<>(false, "Failed to like collection", null));
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieCollection> call, @NonNull Throwable t) {
                likeResult.setValue(new ApiResponse<>(false, "Something went wrong.", null));
            }
        });
    }

    public void unlikeMovieCollection(MovieCollectionLikeInput input) {
        Call<MovieCollection> call = apiService.unlikeMovieCollection(input);

        call.enqueue(new Callback<MovieCollection>() {
            @Override
            public void onResponse(@NonNull Call<MovieCollection> call, @NonNull Response<MovieCollection> response) {
                if (response.code() == 200) {
                    likeResult.setValue(new ApiResponse<>(true, "Unlike successful", null));
                } else {
                    likeResult.setValue(new ApiResponse<>(false, "Failed to unlike collection", null));
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieCollection> call, @NonNull Throwable t) {
                likeResult.setValue(new ApiResponse<>(false, "Something went wrong.", null));
            }
        });
    }
}
