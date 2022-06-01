package com.kna.appsec.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.kna.appsec.model.History;
import com.ns.odoolib_retrofit.utils.OdooDate;
import com.tsolution.base.BaseViewModel;

public class HistoryFrgVM extends BaseViewModel {

    ObservableList<History> list = new ObservableArrayList<>();

    public HistoryFrgVM(@NonNull Application application) {
        super(application);

        OdooDate odooDate = new OdooDate();
        list.add(new History(odooDate, "BTVN", "100s", "99/100"));
    }

    public ObservableList<History> getList() {
        return list;
    }

    public void setList(ObservableList<History> list) {
        this.list = list;
    }
}
