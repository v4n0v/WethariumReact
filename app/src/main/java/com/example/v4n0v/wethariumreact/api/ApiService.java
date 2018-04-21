package com.example.v4n0v.wethariumreact.api;


import com.example.v4n0v.wethariumreact.entities.WeatherInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    // http://api.openweathermap.org/data/2.5/weather?q=Moscow&units=metric&appid=1aa546d01134ed09d869b84c7e83e34f
//
    @GET()
    Observable<WeatherInfo> getWeather(@Query("appid") String key,
                                       @Query("q") String city,
                                       @Query("units") String units);
//    @GET()
//    Observable<WeatherInfo> getWeather();
}
