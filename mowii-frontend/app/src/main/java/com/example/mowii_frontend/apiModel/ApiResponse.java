package com.example.mowii_frontend.apiModel;


import retrofit2.Response;

public class ApiResponse {
    private final boolean isSuccess;
    private final String errorMessage;

    public ApiResponse(boolean isSuccess, String errorMessage) {
        this.isSuccess = isSuccess;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
