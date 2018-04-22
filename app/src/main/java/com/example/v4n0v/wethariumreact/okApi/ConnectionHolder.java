package com.example.v4n0v.wethariumreact.okApi;

import com.example.v4n0v.wethariumreact.gson.Weather;
import com.example.v4n0v.wethariumreact.gson.WeatherDeserializer;
import com.example.v4n0v.wethariumreact.gson.WeatherMain;
import com.example.v4n0v.wethariumreact.gson.WeatherMainDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URL;

import io.reactivex.Observable;

public class ConnectionHolder {
    private static final String OPEN_API_MAP = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";
    private static final String OPEN_API_KEY = "1aa546d01134ed09d869b84c7e83e34f";
    private ConnectionCore core;

    private Gson gson;



    public ConnectionHolder() {
        core=ConnectionCore.getConnection();
         gson = new GsonBuilder()
                .registerTypeAdapter(Weather.class, new WeatherDeserializer())
                .registerTypeAdapter(WeatherMain.class, new WeatherMainDeserializer())
                .create();
    }


    public Observable<Weather> getWeather(String city) {
        return Observable.fromCallable(() -> {
            URL url = new URL(String.format(OPEN_API_MAP, city, OPEN_API_KEY) + "&appid=" + OPEN_API_KEY);
            return gson.fromJson(core.getResponse(url), Weather.class);
        });
    }





}
