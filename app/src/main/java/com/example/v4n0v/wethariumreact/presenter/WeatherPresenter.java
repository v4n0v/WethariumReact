package com.example.v4n0v.wethariumreact.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.v4n0v.wethariumreact.entities.WeatherInfo;
import com.example.v4n0v.wethariumreact.repos.WeatherRepo;
import com.example.v4n0v.wethariumreact.views.WeatherView;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


@InjectViewState
public class WeatherPresenter extends MvpPresenter<WeatherView> {
    WeatherRepo weatherRepo;
    private Scheduler scheduler;
    WeatherInfo weatherInfo;

    public WeatherPresenter(Scheduler scheduler) {

        this.scheduler = scheduler;
    }

    public void init(){
        Timber.d("Init WeatherPresenter");
        weatherRepo.getWeather("Moscow")
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe(weather -> {
                   weatherInfo=weather;
                   Timber.d("weather ") ;
                });
    }
}
