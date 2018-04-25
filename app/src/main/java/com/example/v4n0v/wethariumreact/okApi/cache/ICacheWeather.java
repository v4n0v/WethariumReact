package com.example.v4n0v.wethariumreact.okApi.cache;

import com.example.v4n0v.wethariumreact.entities.gson.Weather;

import java.util.List;

import io.reactivex.ObservableEmitter;


public interface ICacheWeather {
    void readWeatherFromCache(String city, ObservableEmitter<Weather> e);
    void writeWeatherToCache( Weather weather);

    void readWeathersListFromCache(  ObservableEmitter<List<Weather>> e);
    void clearBase( ObservableEmitter<Boolean> e);

}
