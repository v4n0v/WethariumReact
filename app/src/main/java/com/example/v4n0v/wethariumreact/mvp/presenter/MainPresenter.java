package com.example.v4n0v.wethariumreact.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.v4n0v.wethariumreact.common.ChangeCityBus;
import com.example.v4n0v.wethariumreact.common.NetworkStatus;
import com.example.v4n0v.wethariumreact.common.WeatherBroadcastBus;
import com.example.v4n0v.wethariumreact.entities.WeatherInfo;
import com.example.v4n0v.wethariumreact.entities.gson.Weather;
import com.example.v4n0v.wethariumreact.mvp.model.repos.WeatherRepo;
import com.example.v4n0v.wethariumreact.mvp.views.MainView;
import com.example.v4n0v.wethariumreact.okApi.ConnectionHolder;
import com.example.v4n0v.wethariumreact.okApi.cache.ICacheWeather;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import io.paperdb.Paper;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    private WeatherRepo weatherRepo;
    private Scheduler scheduler;
    private Weather weather;

    private WeatherInfo weatherInfo;

    @Inject
    ConnectionHolder connection;

    @Inject
    ICacheWeather cacheWeather;

    // подписываюсь на рассылку сообщений при получении погодыд от сервиса
    public MainPresenter(Scheduler scheduler) {

        this.scheduler = scheduler;

    }

    public void init() {
        Timber.d("Init MainPresenter");
        //getViewState().connectService(loadData());
        weather = Paper.book("weather").read("weather", null);
        if (weather != null) {
//            observePhotos(weather.getCity());
            getViewState().loadCityImage(weather.getCity());
        }

    }

    // загрушаем последний город из кеша
    private String loadData() {
        return Paper.book("city").read("city", "Moscow");
    }


    // получаю eventbus с собщением о погоде от сервиса
    @Subscribe
    public void recievedMessage(Weather msg) {
        weather = msg;

        //observePhotos(weather.getCity());
        getViewState().loadCityImage(weather.getCity());
        Timber.d("WeatherBroadcastBus message " + weather.getCity() + ", temp = " + weather.getTemperature());
        getViewState().toast(weather.getCity() + ", temp = " + weather.getTemperature());
    }

    void observePhotos(String city) {
        if (NetworkStatus.isOnline()) {
            connection.getPhotoLinks(city)
                    .observeOn(scheduler)
                    .subscribeOn(Schedulers.io())
                    .subscribe(link -> {
                        Timber.d("I've got " + link + " link");
                        getViewState().loadCityImage(city);
                    });
        }
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

    public void subscribeBus(){
        WeatherBroadcastBus.getBus().register(this);
    }
    public void unsubscribeBus(){
        WeatherBroadcastBus.getBus().unregister(this);
    }

    public void reloadPhoto() {
        //weather = Paper.book("weather").read("weather", null);
        getViewState().reloadCityPhoto(weather.getCity());
    }
}
