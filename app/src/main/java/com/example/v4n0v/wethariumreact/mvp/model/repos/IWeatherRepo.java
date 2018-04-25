package com.example.v4n0v.wethariumreact.mvp.model.repos;


import com.example.v4n0v.wethariumreact.entities.WeatherInfo;
import com.example.v4n0v.wethariumreact.entities.gson.Weather;

import io.reactivex.Observable;


public interface IWeatherRepo  {
    Observable<WeatherInfo> getWeather(String username);
}
