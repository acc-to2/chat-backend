package com.totwo.chat.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

public record CommonResponse<T>(
        boolean success,
        @Nullable T data,
        @Nullable CustomError error
) {

    public static <T> ResponseEntity<CommonResponse<T>> ok(@Nullable final T data) {
        return ResponseEntity.status(HttpStatus.OK).body(new CommonResponse<>(true, data, null));
    }

    public static <T> ResponseEntity<CommonResponse<T>> created(@Nullable final T data) {
        return  ResponseEntity.status(HttpStatus.CREATED).body(new CommonResponse<>(true, data, null));
    }

    public static <T> ResponseEntity<CommonResponse<T>> failed(final CustomError error) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse<>(false, null, error));
    }

    public static <T> ResponseEntity<CommonResponse<T>> onFailure(@Nullable final T data, final CustomError error) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse<>(false, data, error));
    }

    public static <T> ResponseEntity<CommonResponse<T>> exception(final CustomError error) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse<>(false, null, error));
    }

    public static <T> ResponseEntity<CommonResponse<T>> onException(@Nullable final T data, final CustomError error) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse<>(false, data, error));
    }
}
