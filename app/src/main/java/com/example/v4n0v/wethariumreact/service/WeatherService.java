package com.example.v4n0v.wethariumreact.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.v4n0v.wethariumreact.App;
import com.example.v4n0v.wethariumreact.common.ChangeCityBus;
import com.example.v4n0v.wethariumreact.common.WeatherBroadcastBus;
import com.example.v4n0v.wethariumreact.entities.gson.Weather;
import com.example.v4n0v.wethariumreact.okApi.ConnectionHolder;
import com.squareup.otto.Subscribe;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import io.paperdb.Paper;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


public class WeatherService extends Service {
    private long interval = 600_000;
    private String TAG = "WeatherService";
    private Timer timer;
    private TimerTask tTask;
    private WeatherBinder binder = new WeatherBinder();
    private String city;
    private boolean isComplete;

    @Inject
    ConnectionHolder connectionHolder;

    @Override
    public void onCreate() {
        super.onCreate();
        ChangeCityBus.getBus().register(this);
        App.getInstance().getAppComponent().inject(this);
        timer = new Timer();
        Log.d(TAG, "ServiceWeather created");
        schedule();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "Service bind");
        return binder;
    }

    public void changeCity(String city) {
        this.city = city;
        isComplete = false;
        schedule();
    }

    protected void schedule() {
        if (tTask != null) tTask.cancel();
        if (interval > 0) {
            // начинаем отсчет до 10минут
            tTask = new TimerTask() {
                public void run() {
                    Log.d(TAG, "Service weather interval " + interval);
                    if (city != null) {
                        if (!isComplete) {

                                Observable<Weather> single = connectionHolder.getWeather(city);
                                single.subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(weather -> {
                                            WeatherBroadcastBus.getBus().post(weather);
                                            Paper.book("city").write("city", weather.getCity());
                                            Timber.d(weather.getCity() + ", temp = " + weather.getTemperature());

                                        });

                        }
                    }
                }
            };
            timer.schedule(tTask, 1000, interval);
        }
    }

    @Subscribe
    public void onRecieve(String city) {

        changeCity(city);
    }

    public class WeatherBinder extends Binder {
        public WeatherService getService() {
            return WeatherService.this;
        }
    }
}
