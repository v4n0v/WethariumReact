package com.example.v4n0v.wethariumreact.repos;


import com.example.v4n0v.wethariumreact.entities.WeatherInfo;

import io.reactivex.Observable;


public interface IWeatherRepo  {
    Observable<WeatherInfo> getWeather(String username);
}