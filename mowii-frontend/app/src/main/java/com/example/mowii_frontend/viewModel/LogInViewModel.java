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
    private final MutableLiveData<ApiResponse<User>> authenticationResult = new MutableLiveData<>();

    public LiveData<ApiResponse<User>> getAuthenticationResult() {
        return authenticationResult;
    }

    private final ApiService apiService = RetrofitClient.createService(ApiService.class);

    public void authenticateUser(User user) {
        Call<User> call = apiService.authenticateUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.code() == 200) {authenticationResult.setValue(new ApiResponse<User>(true, "", response.body())); }
                else authenticationResult.setValue(new ApiResponse<User>(false, "Invalid Credentials.", null));
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                authenticationResult.setValue(new ApiResponse<User>(false, "Something went wrong.", null));
            }
        });
    }
}
