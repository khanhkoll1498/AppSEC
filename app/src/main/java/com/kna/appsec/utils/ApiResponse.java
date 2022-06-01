package com.kna.appsec.utils;

import static com.kna.appsec.utils.Status.ERROR;
import static com.kna.appsec.utils.Status.LOADING;
import static com.kna.appsec.utils.Status.NOT_CONNECT;
import static com.kna.appsec.utils.Status.SUCCESS;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class ApiResponse {
    public final Status status;
    @Nullable
    public final Throwable error;
    Object object;

    private ApiResponse(Status status, Object o, @Nullable Throwable error) {
        this.status = status;
        this.error = error;
        this.object = o;
    }

    public static ApiResponse loading() {
        return new ApiResponse(LOADING, null, null);
    }

    public static ApiResponse success(Object o) {
        return new ApiResponse(SUCCESS, o, null);
    }

    public static ApiResponse error(@NonNull Throwable error) {
        return new ApiResponse(ERROR, null, error);
    }

    public static ApiResponse notConnect(@NonNull Throwable error) {
        return new ApiResponse(NOT_CONNECT, null, error);
    }

}
