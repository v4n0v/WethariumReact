package com.example.v4n0v.wethariumreact.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.example.v4n0v.wethariumreact.entities.gson.Weather;


public interface WeatherView extends MvpView {

    void applyData(Weather weather);
    void showDescription(int description);

    void showIcon(int weatherIcon);
    void showLastUpdate(String lastUpd);

    void showTemperature(String temperature);

    void showTemperatureBetween(String temperatureBetween);
}
