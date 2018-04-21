package com.example.v4n0v.wethariumreact.mvp.repos;

import com.example.v4n0v.wethariumreact.api.ApiHolder;
import com.example.v4n0v.wethariumreact.common.Resource;
import com.example.v4n0v.wethariumreact.gson.Weather;

import io.reactivex.Observable;


public class WeatherRepo implements IWeatherRepo{

    @Override
    public Observable<Weather> getWeather(String city) {
        return ApiHolder.getApi().getWeather(city, Resource.KEY);
    }


}
