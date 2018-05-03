package com.example.v4n0v.wethariumreact.mvp.views;

import com.arellomobile.mvp.MvpView;

/**
 * WeatherHistoryView интерфейс
 */

public interface WeatherHistoryView extends MvpView {
    void init();
    void updateList();

    void startActivity();
    void toast(String msg);
}
