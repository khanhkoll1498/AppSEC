package com.kna.appsec.base;


public interface IResponse<Result>{
    void onSuccess(Result result);
    void onFail(Throwable error);
}
