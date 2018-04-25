package com.example.v4n0v.wethariumreact.mvp.views;

import com.arellomobile.mvp.MvpView;

/**
 * Created by v4n0v on 25.04.18.
 */

public interface WeatherHistoryView extends MvpView {
    void updateList();

    void startActivity();
    void toast(String msg);
}
