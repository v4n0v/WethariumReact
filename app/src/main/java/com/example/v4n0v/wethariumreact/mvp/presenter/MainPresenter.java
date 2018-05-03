package com.example.v4n0v.wethariumreact.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.v4n0v.wethariumreact.common.ChangeCityBus;
import com.example.v4n0v.wethariumreact.entities.gson.Weather;
import com.example.v4n0v.wethariumreact.mvp.views.MainView;
import com.example.v4n0v.wethariumreact.okApi.ConnectionHolder;
import com.example.v4n0v.wethariumreact.okApi.cache.ICacheWeather;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import io.paperdb.Paper;
import io.reactivex.Scheduler;
import timber.log.Timber;


@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private Scheduler scheduler;
    private Weather weather;

    @Inject
    ConnectionHolder connection;

    @Inject
    ICacheWeather cacheWeather;

    // подписываюсь на рассылку сообщений при получении погодыд от сервиса
    public MainPresenter(Scheduler scheduler) {

        this.scheduler = scheduler;

    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        init();
        getViewState().init();
    }

    public void init() {
        Timber.d("Init MainPresenter");
        weather = Paper.book("weather").read("weather", null);
        if (weather != null) {
            getViewState().loadCityImage(weather.getCity());
        }

    }


    // получаю eventbus с собщением о погоде от сервиса
    @Subscribe
    public void recievedMessage(Weather msg) {
        weather = msg;
        getViewState().loadCityImage(weather.getCity());
        Timber.d("WeatherBroadcastBus message " + weather.getCity() + ", temp = " + weather.getTemperature());
        getViewState().toast(weather.getCity() + ", temp = " + weather.getTemperature());
    }


    // отпавляю в сервис ссобщение о смене города, записываю в базу текущий город
    public void changeCity(String city) {
        ChangeCityBus.getBus().post(city);
        Paper.book("city").write("city", city);
    }

    public void update() {
        String city = Paper.book("city").read("city");
        ChangeCityBus.getBus().post(city);
    }


    public void selectCity() {
        String city = Paper.book("city").read("city");
        getViewState().selectCityDialog(city);
    }

    public void reloadPhoto() {
        getViewState().reloadCityPhoto(weather.getCity());
    }
}
