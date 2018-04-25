package com.example.v4n0v.wethariumreact.dagger.modules;

import com.example.v4n0v.wethariumreact.entities.gson.Weather;
import com.example.v4n0v.wethariumreact.entities.gson.WeatherDeserializer;
import com.example.v4n0v.wethariumreact.entities.gson.WeatherMain;
import com.example.v4n0v.wethariumreact.entities.gson.WeatherMainDeserializer;
import com.example.v4n0v.wethariumreact.okApi.ConnectionCore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class ConnectionCoreModule {

    @Provides
    public ConnectionCore connectionCore() {
        return new ConnectionCore();
    }

    @Provides
    Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(Weather.class, new WeatherDeserializer())
                .registerTypeAdapter(WeatherMain.class, new WeatherMainDeserializer())
                .create();
    }

}
