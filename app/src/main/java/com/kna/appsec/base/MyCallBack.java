package com.kna.appsec.base;

import com.workable.errorhandler.ErrorHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyCallBack<T> implements Callback<T> {

    IResponse result;

    public MyCallBack(IResponse result) {
        this.result = result;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.errorBody() != null) {
            ErrorHandler.create().handle(new Exception(response.errorBody().toString()));
            return;
        }
        result.onSuccess(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        ErrorHandler.create().handle(t);
    }
}
