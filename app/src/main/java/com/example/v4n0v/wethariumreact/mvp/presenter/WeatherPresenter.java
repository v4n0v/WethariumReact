package com.example.v4n0v.wethariumreact.mvp.presenter;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.v4n0v.wethariumreact.common.WeatherBroadcastBus;
import com.example.v4n0v.wethariumreact.entities.gson.Weather;
import com.example.v4n0v.wethariumreact.common.WeatherDecorator;
import com.example.v4n0v.wethariumreact.mvp.views.WeatherView;
import com.squareup.otto.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Locale;

import io.paperdb.Paper;
import io.reactivex.Scheduler;


@InjectViewState
public class WeatherPresenter extends MvpPresenter<WeatherView> {
    Scheduler scheduler;
    WeatherDecorator model;
    public WeatherPresenter(Scheduler scheduler, Context context) {
        this.scheduler = scheduler;
        this.model=new WeatherDecorator(context);
        WeatherBroadcastBus.getBus().register(this);
    }

    public void init(){
        Weather weather = Paper.book("weather").read("weather", null);
        if (weather!=null){
           applyData(weather);
        }
    }

   private void  applyData(Weather weather){
       getViewState().applyData(weather);
       getViewState().showIcon(weather.getId());
       getViewState().showDescription(weather.getId());
       getViewState().showTemperature(model.temperatureFormat(weather.getTemperature()));
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MMM: HH:mm", Locale.getDefault());
       getViewState().showLastUpdate(dateFormat.format(weather.getTime()));
       getViewState().showTemperatureBetween(model.getTemperatureBetween(weather.getTempMin(), weather.getTempMax()));
    }

    @Subscribe
    public void onRecieve(Weather weather){
        applyData(weather);
    }





}
