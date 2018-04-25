package com.example.v4n0v.wethariumreact.dagger.modules;

import com.example.v4n0v.wethariumreact.okApi.ConnectionCore;
import com.example.v4n0v.wethariumreact.okApi.ConnectionHolder;
import com.example.v4n0v.wethariumreact.okApi.cache.ICacheWeather;
import com.example.v4n0v.wethariumreact.okApi.cache.PaperWeatherCache;
import com.example.v4n0v.wethariumreact.okApi.cache.RealmWeatherCache;
import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module(includes = {ConnectionCoreModule.class, CacheModule.class})
public class ConnectionModule {

    @Provides
    public ConnectionHolder holder(ConnectionCore coreModule, Gson gson, ICacheWeather cacheWeather){return new ConnectionHolder(coreModule, gson,cacheWeather );}

}
