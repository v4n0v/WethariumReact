package com.example.v4n0v.wethariumreact.mvp.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.v4n0v.wethariumreact.gson.Weather;
import com.example.v4n0v.wethariumreact.gson.WeatherDeserializer;
import com.example.v4n0v.wethariumreact.gson.WeatherMain;
import com.example.v4n0v.wethariumreact.gson.WeatherMainDeserializer;
import com.example.v4n0v.wethariumreact.mvp.repos.WeatherRepo;
import com.example.v4n0v.wethariumreact.mvp.views.WeatherView;
import com.example.v4n0v.wethariumreact.okApi.ConnectionHolder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


@InjectViewState
public class WeatherPresenter extends MvpPresenter<WeatherView> {
    WeatherRepo weatherRepo;
    private Scheduler scheduler;
    Weather weatherInfo;
    String TAG = "WeatherPresenter";
    ConnectionHolder connectionManager;


    public WeatherPresenter(Scheduler scheduler) {

        this.scheduler = scheduler;
        connectionManager = new ConnectionHolder();
    }

    public void init() {
        Log.d(TAG, "Init WeatherPresenter");
        Timber.d("Init WeatherPresenter");
        // через ретрофит
//        weatherRepo.getWeather("Moscow")
//                .subscribeOn(Schedulers.io())
//                .observeOn(scheduler)
//                .subscribe(weather -> {
//                    //  weatherInfo=weather;
//                    Timber.d("weather ");
//                });

        // через OkHttpClient все получается и сериализуется, но это "не спортивно"

        getWeatherFromOk("Moscow");
    }


    public void getWeatherFromOk(String city) {

        Observable<Weather> single = connectionManager.getWeather(city);
                single.subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe(s -> {
                    weatherInfo = s;
                    //todo timber отказывается писать в лог
                    Log.d(TAG, weatherInfo.getCity() + ", temp = " + weatherInfo.getTemperature());
                });


    }

}
