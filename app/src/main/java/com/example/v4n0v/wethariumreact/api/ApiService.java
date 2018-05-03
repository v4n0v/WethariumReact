package com.example.v4n0v.wethariumreact.api;


import com.example.v4n0v.wethariumreact.entities.pojo.WeatherInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    // http://api.openweathermap.org/data/2.5/weather?q=Moscow&units=metric&appid=1aa546d01134ed09d869b84c7e83e34f
//
    @GET("/weather?units=metric")
    Observable<WeatherInfo> getWeather(@Query("q") String city,
                                       @Query("appid") String key);
    @GET()
    Observable<WeatherInfo> getWeather();
}
