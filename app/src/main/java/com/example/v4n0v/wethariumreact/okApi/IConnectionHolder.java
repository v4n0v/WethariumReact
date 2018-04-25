package com.example.v4n0v.wethariumreact.okApi;

import com.example.v4n0v.wethariumreact.entities.gson.Weather;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by v4n0v on 22.04.18.
 */

public interface IConnectionHolder {
    Observable<Weather> getWeather(String city);
    Observable<String> getPhotoLinks(String city);
}
