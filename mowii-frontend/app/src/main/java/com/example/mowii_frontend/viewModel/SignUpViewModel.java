package com.example.mowii_frontend.viewModel;
import android.util.Log;

import com.example.mowii_frontend.api.ApiService;
import com.example.mowii_frontend.api.RetrofitClient;
import com.example.mowii_frontend.model.User;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends ViewModel {

    private final ApiService apiService = RetrofitClient.createService(ApiService.class);

    public void registerUser(User user) {
        Call<Void> call = apiService.registerUser(user);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                // Handle successful registration
                Log.i("REG", "onResponse: ");
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                // Handle registration failure
                Log.i("REG", "onFailure: " + t.getMessage());
            }
        });
    }
}

