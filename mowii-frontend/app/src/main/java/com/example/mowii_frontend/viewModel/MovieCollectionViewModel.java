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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieCollectionViewModel extends ViewModel {
    private final MutableLiveData<ApiResponse<ArrayList<MovieCollection>>> allCollectionsResult = new MutableLiveData<>();
    private final MutableLiveData<ApiResponse<ArrayList<MovieCollection>>> userCollectionsResult = new MutableLiveData<>();
    private final MutableLiveData<ApiResponse<MovieCollection>> likeCollectionResult = new MutableLiveData<>();
    private final MutableLiveData<ApiResponse<MovieCollection>> unlikeCollectionResult = new MutableLiveData<>();

    public LiveData<ApiResponse<ArrayList<MovieCollection>>> getAllCollectionsResult() {
        return allCollectionsResult;
    }

    public LiveData<ApiResponse<ArrayList<MovieCollection>>> getUserCollectionsResult() {
        return userCollectionsResult;
    }

    public LiveData<ApiResponse<MovieCollection>> likeCollectionResult() {
        return likeCollectionResult;
    }

    public LiveData<ApiResponse<MovieCollection>> unlikeCollectionResult(){
        return unlikeCollectionResult;
    }

    private final ApiService apiService = RetrofitClient.createService(ApiService.class);

    public void getAllCollections() {
        Call<ArrayList<MovieCollection>> call = apiService.getAllMovieCollections();
        call.enqueue(new Callback<ArrayList<MovieCollection>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<MovieCollection>> call, @NonNull Response<ArrayList<MovieCollection>> response) {
                if (response.isSuccessful()) { allCollectionsResult.setValue(new ApiResponse<ArrayList<MovieCollection>>(true, "", response.body())); }
                else allCollectionsResult.setValue(new ApiResponse<>(false, "Something went wrong.", null));
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<MovieCollection>> call, @NonNull Throwable t) {
                allCollectionsResult.setValue(new ApiResponse<>(false, "Something went wrong.", null));
            }
        });
    }

    public void getUserCollections(String userId) {
        Call<ArrayList<MovieCollection>> call = apiService.getAllMovieCollectionsByUserId(userId);
        call.enqueue(new Callback<ArrayList<MovieCollection>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<MovieCollection>> call, @NonNull Response<ArrayList<MovieCollection>> response) {
                if (response.isSuccessful()) { userCollectionsResult.setValue(new ApiResponse<ArrayList<MovieCollection>>(true, "", response.body())); }
                else userCollectionsResult.setValue(new ApiResponse<>(false, "Something went wrong.", null));
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<MovieCollection>> call, @NonNull Throwable t) {
                userCollectionsResult.setValue(new ApiResponse<>(false, "Something went wrong.", null));
            }
        });
    }

    public void likeCollection(String collectionId, String likerId){
        Call<MovieCollection> call = apiService.likeMovieCollection(new MovieCollectionLikeInput(collectionId, likerId));
        call.enqueue(new Callback<MovieCollection>() {
            @Override
            public void onResponse(@NonNull Call<MovieCollection> call, @NonNull Response<MovieCollection> response) {
                if (response.isSuccessful()) { likeCollectionResult.setValue(new ApiResponse<MovieCollection>(true, "", response.body())); }
                else likeCollectionResult.setValue(new ApiResponse<>(false, "Something went wrong.", null));
            }

            @Override
            public void onFailure(@NonNull Call<MovieCollection> call, @NonNull Throwable t) {
                likeCollectionResult.setValue(new ApiResponse<>(false, "Something went wrong.", null));
            }
        });
    }

    public void unlikeCollection(String collectionId, String likerId){
        Call<MovieCollection> call = apiService.unlikeMovieCollection(new MovieCollectionLikeInput(collectionId, likerId));
        call.enqueue(new Callback<MovieCollection>() {
            @Override
            public void onResponse(@NonNull Call<MovieCollection> call, @NonNull Response<MovieCollection> response) {
                if (response.isSuccessful()) { unlikeCollectionResult.setValue(new ApiResponse<MovieCollection>(true, "", response.body())); }
                else unlikeCollectionResult.setValue(new ApiResponse<>(false, "Something went wrong.", null));
            }

            @Override
            public void onFailure(@NonNull Call<MovieCollection> call, @NonNull Throwable t) {
                unlikeCollectionResult.setValue(new ApiResponse<>(false, "Something went wrong.", null));
            }
        });
    }
}
