package com.kna.appsec.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.kna.appsec.base.RunUi;
import com.kna.appsec.model.Count;
import com.tsolution.base.BaseViewModel;

public class TestWordFrgVM extends BaseViewModel {

    ObservableList<Count> list = new ObservableArrayList<>();

    public TestWordFrgVM(@NonNull Application application) {
        super(application);

        list.add(new Count("1-50"));
        list.add(new Count("51-100"));
        list.add(new Count("101-150"));
        list.add(new Count("151-200"));
        list.add(new Count("201-250"));
        list.add(new Count("251-300"));
        list.add(new Count("301-350"));
        list.add(new Count("351-400"));
        list.add(new Count("401-450"));
        list.add(new Count("451-500"));
    }

    public ObservableList<Count> getList() {
        return list;
    }

    public void setList(ObservableList<Count> list) {
        this.list = list;
    }

    public void getData(RunUi runUi) {
        runUi.run("getData");
    }
}
