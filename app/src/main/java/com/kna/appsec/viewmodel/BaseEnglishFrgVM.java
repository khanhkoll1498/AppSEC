package com.kna.appsec.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.kna.appsec.base.RunUi;
import com.kna.appsec.model.Topic;
import com.tsolution.base.BaseViewModel;


public class BaseEnglishFrgVM extends BaseViewModel {

    ObservableList<Topic> list = new ObservableArrayList<>();
    ObservableList<Topic> listClause = new ObservableArrayList<>();
    ObservableList<Topic> listPronunciation = new ObservableArrayList<>();

    public BaseEnglishFrgVM(@NonNull Application application) {
        super(application);

        list.add(new Topic("1", "Danh từ"));
        list.add(new Topic("2", "Tính từ"));
        list.add(new Topic("3", "Động từ"));
        list.add(new Topic("4", "Trạng từ"));
        list.add(new Topic("5", "Giới từ"));
        list.add(new Topic("6", "Kiến thức liên quan"));
        list.add(new Topic("7", "Quy tắc chính tả \nĐộng từ bất quy tắc \nCụm động từ"));
        list.add(new Topic("8", "Tổng quan tiếng anh"));

        listClause.add(new Topic("1", "Mệnh đề tính ngữ"));
        listClause.add(new Topic("2", "Mệnh đề trạng ngữ"));
        listClause.add(new Topic("3", "Mệnh đề danh ngữ"));
        listClause.add(new Topic("4", "Tổng quan mệnh đề"));

        listPronunciation.add(new Topic("1", "Hiện tượng âm trong từ"));
        listPronunciation.add(new Topic("2", "Hiện tượng âm thanh câu"));
    }

    public ObservableList<Topic> getList() {
        return list;
    }

    public void setList(ObservableList<Topic> list) {
        this.list = list;
    }

    public ObservableList<Topic> getListClause() {
        return listClause;
    }

    public void setListClause(ObservableList<Topic> listClause) {
        this.listClause = listClause;
    }

    public ObservableList<Topic> getListPronunciation() {
        return listPronunciation;
    }

    public void setListPronunciation(ObservableList<Topic> listPronunciation) {
        this.listPronunciation = listPronunciation;
    }

    public void getData(RunUi runUi) {
        runUi.run("getData");
    }
}
