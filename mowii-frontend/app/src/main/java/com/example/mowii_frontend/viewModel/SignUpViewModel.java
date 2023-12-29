package com.example.mowii_frontend.viewModel;

import com.example.mowii_frontend.api.ApiService;
import com.example.mowii_frontend.api.RetrofitClient;
import com.example.mowii_frontend.api.ApiResponse;
import com.example.mowii_frontend.model.User;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends ViewModel {
    private final MutableLiveData<ApiResponse<User>> registrationResult = new MutableLiveData<>();

    public LiveData<ApiResponse<User>> getAuthenticationResult() {
        return registrationResult;
    }

    private final ApiService apiService = RetrofitClient.createService(ApiService.class);

    public void registerUser(User user) {
        Call<User> call = apiService.registerUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.code() == 200) { registrationResult.setValue(new ApiResponse<User>(true, "", response.body())); }
                else if(response.code() == 400) {registrationResult.setValue(new ApiResponse<User>(false, "Email is already taken.",null));}
                else  registrationResult.setValue(new ApiResponse<User>(false, "Registration failed.", null));
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                registrationResult.setValue(new ApiResponse<User>(false, "Something went wrong.", null));
            }
        });
    }
}

