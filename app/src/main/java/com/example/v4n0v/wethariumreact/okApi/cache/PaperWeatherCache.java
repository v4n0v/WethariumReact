package com.example.v4n0v.wethariumreact.okApi.cache;

import com.example.v4n0v.wethariumreact.entities.gson.Weather;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.ObservableEmitter;


public class PaperWeatherCache implements ICacheWeather {
    @Override
    public void readWeatherFromCache(String city, ObservableEmitter<Weather> e) {
        Weather weather = Paper.book("weather").read(city);
        e.onNext(weather);
        e.onComplete();
    }

    @Override
    public void writeWeatherToCache(Weather weather) {
        Paper.book().write(weather.getCity(), weather);
    }

    @Override
    public void readWeathersListFromCache(ObservableEmitter<List<Weather>> e) {
        List<String>keys = Paper.book("weather").getAllKeys();
        List<Weather> list = new ArrayList<>();
        for (String key: keys){
            list.add(Paper.book("weather").read(key));
        }
        e.onNext(list);
        e.onComplete();
    }

    @Override
    public void clearBase(ObservableEmitter<Boolean> e) {
        Paper.book("weather").destroy();
        e.onNext(true);
    }




}
