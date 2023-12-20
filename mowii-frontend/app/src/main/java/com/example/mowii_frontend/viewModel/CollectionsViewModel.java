package com.example.mowii_frontend.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mowii_frontend.api.ApiService;
import com.example.mowii_frontend.api.RetrofitClient;
import com.example.mowii_frontend.apiModel.ApiResponse;
import com.example.mowii_frontend.model.MovieCollection;
import com.example.mowii_frontend.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectionsViewModel extends ViewModel {
    private final MutableLiveData<ApiResponse<ArrayList<MovieCollection>>> allCollectionsResult = new MutableLiveData<>();
    private final MutableLiveData<ApiResponse<ArrayList<MovieCollection>>> myCollectionsResult = new MutableLiveData<>();

    public LiveData<ApiResponse<ArrayList<MovieCollection>>> getAllCollectionsResult() {
        return allCollectionsResult;
    }

    public LiveData<ApiResponse<ArrayList<MovieCollection>>> getMyCollectionsResult() {
        return myCollectionsResult;
    }

    private final ApiService apiService = RetrofitClient.createService(ApiService.class);

    public void getAllCollections() {
        Call<ArrayList<MovieCollection>> call = apiService.getAllMovieCollections();
        call.enqueue(new Callback<ArrayList<MovieCollection>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<MovieCollection>> call, @NonNull Response<ArrayList<MovieCollection>> response) {
                if (response.isSuccessful()) { allCollectionsResult.setValue(new ApiResponse<ArrayList<MovieCollection>>(true, "", response.body())); }
                else allCollectionsResult.setValue(new ApiResponse<ArrayList<MovieCollection>>(false, "Something went wrong.", null));
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<MovieCollection>> call, @NonNull Throwable t) {
                allCollectionsResult.setValue(new ApiResponse<ArrayList<MovieCollection>>(false, "Something went wrong.", null));
            }
        });
    }


    public void getMyCollections(User user) {
        Call<ArrayList<MovieCollection>> call = apiService.getMovieCollectionsByUser(user.getId());
        call.enqueue(new Callback<ArrayList<MovieCollection>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<MovieCollection>> call, @NonNull Response<ArrayList<MovieCollection>> response) {
                if (response.isSuccessful()) { myCollectionsResult.setValue(new ApiResponse<ArrayList<MovieCollection>>(true, "", response.body())); }
                else myCollectionsResult.setValue(new ApiResponse<ArrayList<MovieCollection>>(false, "Something went wrong.", null));
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<MovieCollection>> call, @NonNull Throwable t) {
                myCollectionsResult.setValue(new ApiResponse<ArrayList<MovieCollection>>(false, "Something went wrong.", null));
            }
        });
    }

}
