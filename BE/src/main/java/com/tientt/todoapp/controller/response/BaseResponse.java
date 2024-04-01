package com.tientt.todoapp.controller.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class BaseResponse<T> {

    private int statusCode;
    private String message;
    boolean success = false;
    /**
     * Can be a hashmap or list Spring will render a nice Json response :-)
     *
     */
    private T data;

    public BaseResponse(int statCode, String statusDesc) {
        statusCode = statCode;
        message = statusDesc;

        if (statusCode == HttpStatus.OK.value()) {
            success = true;
        }

    }

    public BaseResponse() {
    }

    public static <T> BaseResponse<T> failedResponse(String message) {
        return failedResponse(HttpStatus.BAD_REQUEST.value(), message, null);
    }

    public static <T> BaseResponse<T> failedResponse(T data) {
        return failedResponse(HttpStatus.BAD_REQUEST.value(), "Bad request", data);
    }

    public static <T> BaseResponse<T> failedResponse(int statusCode, String message) {
        return failedResponse(statusCode, message, null);
    }

    public static <T> BaseResponse<T> failedResponse(int statusCode, String message, T data) {
        BaseResponse<T> response = new BaseResponse<>(statusCode, message);
        response.setSuccess(false);
        response.setData(data);
        return response;
    }

    public static <T> BaseResponse<T> successfulResponse(String message, T data) {
        return successfulResponse(HttpStatus.OK.value(), message, data);
    }

    public static <T> BaseResponse<T> successfulResponse(String message) {
        return successfulResponse(HttpStatus.OK.value(), message, null);
    }

    public static <T> BaseResponse<T> successfulResponse(int statusCode, String message, T data) {
        BaseResponse<T> response = new BaseResponse<>(statusCode, message);
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

}