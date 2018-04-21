package com.example.v4n0v.wethariumreact.mvp.repos;

import com.example.v4n0v.wethariumreact.api.ApiHolder;
import com.example.v4n0v.wethariumreact.common.Resource;
import com.example.v4n0v.wethariumreact.entities.WeatherInfo;

import io.reactivex.Observable;


public class WeatherRepo implements IWeatherRepo{

    @Override
    public Observable<WeatherInfo> getWeather(String city) {
        return ApiHolder.getApi().getWeather(Resource.KEY, city);
    }


}
