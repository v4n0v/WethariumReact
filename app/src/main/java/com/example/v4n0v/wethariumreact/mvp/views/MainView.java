package com.example.v4n0v.wethariumreact.mvp.views;

import com.arellomobile.mvp.MvpView;

/**
 * Created by v4n0v on 21.04.18.
 */

public interface MainView extends MvpView {

    void toast(String msg);
    void snack(String msg);

    void connectService(String city);

    void loadCityImage(String city);

    void selectCityDialog(String city);
}
