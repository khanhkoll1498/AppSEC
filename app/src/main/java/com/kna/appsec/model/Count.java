package com.kna.appsec.model;

import com.tsolution.base.BaseModel;

public class Count extends BaseModel {
    private String count;

    public Count(String count) {
        this.count = count;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
