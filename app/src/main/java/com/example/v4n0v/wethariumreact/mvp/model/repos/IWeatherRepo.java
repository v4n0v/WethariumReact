package com.example.v4n0v.wethariumreact.mvp.model.repos;


import com.example.v4n0v.wethariumreact.entities.pojo.WeatherInfo;

import io.reactivex.Observable;


public interface IWeatherRepo  {
    Observable<WeatherInfo> getWeather(String username);
}
