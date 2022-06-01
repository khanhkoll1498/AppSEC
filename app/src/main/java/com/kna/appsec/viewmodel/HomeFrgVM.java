package com.kna.appsec.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.kna.appsec.model.Topic;
import com.tsolution.base.BaseViewModel;

public class HomeFrgVM extends BaseViewModel {
    ObservableList<Topic> list = new ObservableArrayList<>();

    public ObservableList<Topic> getList() {
        return list;
    }

    public HomeFrgVM(@NonNull Application application) {
        super(application);

        list.add(new Topic("1", "Danh từ"));
        list.add(new Topic("2", "Tính từ"));
        list.add(new Topic("3", "Động từ"));
    }
}
