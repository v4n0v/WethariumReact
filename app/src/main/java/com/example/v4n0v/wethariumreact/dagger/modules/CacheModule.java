package com.example.v4n0v.wethariumreact.dagger.modules;

import com.example.v4n0v.wethariumreact.okApi.cache.ICacheWeather;
import com.example.v4n0v.wethariumreact.okApi.cache.PaperWeatherCache;
import com.example.v4n0v.wethariumreact.okApi.cache.RealmWeatherCache;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Singleton
@Module
public class CacheModule  {

    @Provides
    ICacheWeather cacheWeather(@Named("realm") ICacheWeather cacheWeather){
        return cacheWeather;
    }

    @Provides
    @Named("realm")
    ICacheWeather realmCacheWeather(){
        return new RealmWeatherCache();
    }

    @Provides
    @Named("paper")
    ICacheWeather paperCacheWeather(){
        return new PaperWeatherCache();
    }
}
