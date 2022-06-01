package com.kna.appsec.model;

import com.tsolution.base.BaseModel;

public class Topic extends BaseModel {
    private String stt;
    private String topicNameVietnamese;

    public Topic(String stt, String topicNameVietnamese) {
        this.stt = stt;
        this.topicNameVietnamese = topicNameVietnamese;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getTopicNameVietnamese() {
        return topicNameVietnamese;
    }

    public void setTopicNameVietnamese(String topicNameVietnamese) {
        this.topicNameVietnamese = topicNameVietnamese;
    }
}
