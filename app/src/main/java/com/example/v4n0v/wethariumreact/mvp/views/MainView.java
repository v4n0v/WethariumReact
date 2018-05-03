package com.example.v4n0v.wethariumreact.mvp.views;

import com.arellomobile.mvp.MvpView;

public interface MainView extends MvpView {

    void init();
    void toast(String msg);
    void snack(String msg);

    void connectService(String city);

    void loadCityImage(String city);
    void reloadCityPhoto(String city);
    void selectCityDialog(String city);
}
