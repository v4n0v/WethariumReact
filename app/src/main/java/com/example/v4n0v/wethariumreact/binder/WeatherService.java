package com.example.v4n0v.wethariumreact.binder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.v4n0v.wethariumreact.event_bus.EventBus;
import com.example.v4n0v.wethariumreact.gson.Weather;
import com.example.v4n0v.wethariumreact.gson.WeatherDeserializer;
import com.example.v4n0v.wethariumreact.gson.WeatherMain;
import com.example.v4n0v.wethariumreact.gson.WeatherMainDeserializer;
import com.example.v4n0v.wethariumreact.okApi.ConnectionHolder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class WeatherService extends Service {
    long interval = 10_000;
    String TAG = "WeatherService";
    Timer timer;
    TimerTask tTask;
    WeatherBinder binder = new WeatherBinder();
    String city;
    boolean isComplete;

    ConnectionHolder connectionHolder;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        timer = new Timer();
        connectionHolder=new ConnectionHolder();
        Log.d(TAG, "Service weather created");
        return binder;
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
                                    .subscribe(s -> {
                                       EventBus.getBus().post(s);
                                       Log.d(TAG, s.getCity() + ", temp = " + s.getTemperature());
                                    });
                        }
                    }
                }
            };
            timer.schedule(tTask, 1000, interval);
        }
    }
    public class WeatherBinder extends Binder {
        public WeatherService getService() {
            return WeatherService.this;
        }
    }
}
