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
import com.example.v4n0v.wethariumreact.okApi.ConnectionManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


@InjectViewState
public class WeatherPresenter extends MvpPresenter<WeatherView> {
    WeatherRepo weatherRepo;
    private Scheduler scheduler;
    Weather weatherInfo;
    String TAG = "WeatherPresenter";


    Gson gson = new GsonBuilder()
            .registerTypeAdapter(Weather.class, new WeatherDeserializer())
            .registerTypeAdapter(WeatherMain.class, new WeatherMainDeserializer())
            .create();

    public WeatherPresenter(Scheduler scheduler) {

        this.scheduler = scheduler;
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

        getWeatherFromOk();
    }


    public void getWeatherFromOk() {

        Single<String> single = ConnectionManager.getConnection().getWeather("Moscow");
                single.subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe(s -> {
                    weatherInfo = gson.fromJson(s, Weather.class);
                    //todo timber отказывается писать в лог
                    Timber.d(s);

                    Log.d(TAG, s);
                    Log.d(TAG, weatherInfo.getCity() + ", temp = " + weatherInfo.getTemperature());
                });


    }

}
