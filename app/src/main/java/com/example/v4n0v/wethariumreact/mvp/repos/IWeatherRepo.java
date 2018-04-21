package com.example.v4n0v.wethariumreact.mvp.repos;


import com.example.v4n0v.wethariumreact.gson.Weather;

import io.reactivex.Observable;


public interface IWeatherRepo  {
    Observable<Weather> getWeather(String username);
}
