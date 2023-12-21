package com.example.mowii_frontend.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mowii_frontend.api.ApiService;
import com.example.mowii_frontend.api.RetrofitClient;
import com.example.mowii_frontend.apiModel.ApiResponse;
import com.example.mowii_frontend.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInViewModel extends ViewModel {
    private final MutableLiveData<ApiResponse> authenticationResult = new MutableLiveData<>();

    public LiveData<ApiResponse> getAuthenticationResult() {
        return authenticationResult;
    }

    private final ApiService apiService = RetrofitClient.createService(ApiService.class);

    public void authenticateUser(User user) {
        Call<Void> call = apiService.authenticateUser(user);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.code() == 200) {authenticationResult.setValue(new ApiResponse(true, "", response.toString())); }
                else authenticationResult.setValue(new ApiResponse(false, "Invalid Credentials.", ""));
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                authenticationResult.setValue(new ApiResponse(false, "Something went wrong.", ""));
            }
        });
    }
}
