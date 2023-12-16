package com.example.mowii_frontend.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mowii_frontend.api.ApiService;
import com.example.mowii_frontend.api.RetrofitClient;
import com.example.mowii_frontend.apiModel.ApiResponse;
import com.example.mowii_frontend.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectionsViewModel {
    private final MutableLiveData<ApiResponse> allCollectionsResult = new MutableLiveData<>();
    private final MutableLiveData<ApiResponse> myCollectionsResult = new MutableLiveData<>();

    public LiveData<ApiResponse> getAllCollectionsResult() {
        return allCollectionsResult;
    }

    public LiveData<ApiResponse> getMyCollectionsResult() {
        return myCollectionsResult;
    }

    private final ApiService apiService = RetrofitClient.createService(ApiService.class);

    public void getAllCollections() {
        Call<Void> call = apiService.getAllMovieCollections();

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.code() == 200) { allCollectionsResult.setValue(new ApiResponse(true, "", response.toString())); }
                else allCollectionsResult.setValue(new ApiResponse(false, "Something went wrong.", ""));
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                allCollectionsResult.setValue(new ApiResponse(false, "Something went wrong.", ""));
            }
        });
    }


    public void getMyCollections(User user) {
        Call<Void> call = apiService.getMovieCollectionsByUser(user.getId());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.code() == 200) { myCollectionsResult.setValue(new ApiResponse(true, "", response.toString())); }
                else myCollectionsResult.setValue(new ApiResponse(false, "Something went wrong.", ""));
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                myCollectionsResult.setValue(new ApiResponse(false, "Something went wrong.", ""));
            }
        });
    }

}
