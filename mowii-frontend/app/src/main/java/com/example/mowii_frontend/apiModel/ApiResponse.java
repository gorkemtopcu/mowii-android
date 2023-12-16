package com.example.mowii_frontend.apiModel;
import retrofit2.Response;

public class ApiResponse {
    private final boolean isSuccess;
    private final String errorMessage;
    private final String data;

    public ApiResponse(boolean isSuccess, String errorMessage, String data) {
        this.isSuccess = isSuccess;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getData() { return data; }
}
