package com.kna.appsec.model;

import com.kna.appsec.base.AppController;
import com.ns.odoolib_retrofit.utils.OdooDate;
import com.tsolution.base.BaseModel;

public class History extends BaseModel {
    private OdooDate date;
    private String title;
    private String timeSeconds;
    private String goal;

    public History(OdooDate date, String title, String timeSeconds, String goal) {
        this.date = date;
        this.title = title;
        this.timeSeconds = timeSeconds;
        this.goal = goal;
    }

    public OdooDate getDate() {
        return date;
    }

    public void setDate(OdooDate date) {
        this.date = date;
    }

    public String dayString() {

        String date = AppController.formatDate.format(getDate());

        String day = date.substring(0, 2);

        return day;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimeSeconds() {
        return timeSeconds;
    }

    public void setTimeSeconds(String timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
}
