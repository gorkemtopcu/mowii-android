package com.example.mowii_frontend.apiModel;

public class ApiResponse<T> {
    private final boolean isSuccess;
    private final String errorMessage;
    private final T data;

    public ApiResponse(boolean isSuccess, String errorMessage, T data) {
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

    public T getData() { return data; }
}
