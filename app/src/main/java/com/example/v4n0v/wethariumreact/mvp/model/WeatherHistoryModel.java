package com.example.v4n0v.wethariumreact.mvp.model;

import com.example.v4n0v.wethariumreact.App;
import com.example.v4n0v.wethariumreact.entities.gson.Weather;
import com.example.v4n0v.wethariumreact.okApi.cache.ICacheWeather;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;


public class WeatherHistoryModel {

    private final Scheduler scheduler;
    @Inject
    ICacheWeather cacheWeather;
    List<Weather> weathers;


    public WeatherHistoryModel(Scheduler scheduler) {
        this.scheduler = scheduler;
        App.getInstance().getAppComponent().inject(this);
//        getWeathers()
//                .observeOn(scheduler)
//                .subscribeOn(Schedulers.io())
//                .subscribe(weathers -> {
//                    this.weathers=weathers;
//                });
    }

    public Observable<List<Weather>> getWeathers() {
        return Observable.create(e -> {
            cacheWeather.readWeathersListFromCache(e);
        });
    }

    public Observable<Boolean> clearList(){
        return Observable.create(e -> {
            cacheWeather.clearBase(e);
        });
    }
    public List<Weather> getWeatherList() {
        return weathers;
    }
}
