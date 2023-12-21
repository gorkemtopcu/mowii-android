package com.example.mowii_frontend.viewModel;

import com.example.mowii_frontend.api.ApiService;
import com.example.mowii_frontend.api.RetrofitClient;
import com.example.mowii_frontend.apiModel.ApiResponse;
import com.example.mowii_frontend.model.User;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends ViewModel {
    private final MutableLiveData<ApiResponse> registrationResult = new MutableLiveData<>();

    public LiveData<ApiResponse> getAuthenticationResult() {
        return registrationResult;
    }

    private final ApiService apiService = RetrofitClient.createService(ApiService.class);

    public void registerUser(User user) {
        Call<Void> call = apiService.registerUser(user);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.code() == 200) { registrationResult.setValue(new ApiResponse(true, "", response.toString())); }
                else if(response.code() == 400) {registrationResult.setValue(new ApiResponse(false, "Email is already taken.", response.toString()));}
                else  registrationResult.setValue(new ApiResponse(false, "Registration failed.", ""));
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                registrationResult.setValue(new ApiResponse(false, "Something went wrong.", ""));
            }
        });
    }
}

