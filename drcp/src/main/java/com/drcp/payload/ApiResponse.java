package com.drcp.payload;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private boolean success;
    private int status;
    private String message;
    private T data;
    private LocalDateTime timestamp;
    private Object errors;

    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
        this.success = true;
        this.status = 200;
    }

    public ApiResponse(boolean success, String message, T data) {
        this();
        this.success = success;
        this.status = success ? 200 : 400;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(boolean success, int status, String message, T data) {
        this();
        this.success = success;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(boolean success, int status, String message, T data, Object errors) {
        this(success, status, message, data);
        this.errors = errors;
    }

    public ApiResponse(boolean success, int status, String message, T data, LocalDateTime timestamp, Object errors) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = timestamp != null ? timestamp : LocalDateTime.now();
        this.errors = errors;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, 200, "Success", data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, 200, message, data);
    }

    public static <T> ApiResponse<T> success(int status, String message, T data) {
        return new ApiResponse<>(true, status, message, data);
    }

    public static <T> ApiResponse<T> error(int status, String message) {
        return new ApiResponse<>(false, status, message, null);
    }

    public static <T> ApiResponse<T> error(int status, String message, Object errors) {
        return new ApiResponse<>(false, status, message, null, errors);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }
}