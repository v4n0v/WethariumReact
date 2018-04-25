package com.example.v4n0v.wethariumreact.okApi;

import android.util.Log;

import com.example.v4n0v.wethariumreact.common.NetworkStatus;
import com.example.v4n0v.wethariumreact.entities.gson.Weather;
import com.example.v4n0v.wethariumreact.image.ICacheImage;
import com.example.v4n0v.wethariumreact.okApi.cache.ICacheWeather;
import com.google.gson.Gson;

import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.Observable;
import timber.log.Timber;

public class ConnectionHolder implements IConnectionHolder {

    private static final String OPEN_API_MAP = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";
    private static final String OPEN_API_KEY = "1aa546d01134ed09d869b84c7e83e34f";

    private final String FLIKR_KEY = "9c6b4a5f6ad93dafa5a5ca0ef3b2f864";
    private final int IMAGE_COUNT = 50;
    private final String IMAGE_SIZE = "url_m";
    private final String FLIKR_REQUEST_IMAGE_KEY = "city";


    private String TAG = "ConnectionHolder";

    private ConnectionCore core;
    private Gson gson;

    private  ICacheWeather cacheWeather;

    public ConnectionHolder(ConnectionCore core, Gson gson, ICacheWeather cacheWeather) {
        this.core = core;
        this.gson = gson;
        this.cacheWeather = cacheWeather;
    }

    @Override
    public Observable<Weather> getWeather(String city) {
        if (NetworkStatus.isOffline()) {
            return Observable.create(e -> {
                        cacheWeather.readWeatherFromCache(city, e);
                    }
            );
        } else {
            return Observable.fromCallable(() -> {
                // поулчаем с сервера
                URL url = new URL(String.format(OPEN_API_MAP, city, OPEN_API_KEY) + "&appid=" + OPEN_API_KEY);
                String json = core.getResponse(url);
                Log.d(TAG, json);
                Weather weather = gson.fromJson(json, Weather.class);
                // пишем в базу
                long time = System.currentTimeMillis();
                weather.setTime(time);
                cacheWeather.writeWeatherToCache(weather);
               // cacheWeather.writeWeathersListToCache(weather);

                return weather;
            });
        }
    }

    @Override
    public Observable<String> getPhotoLinks(String city) {


            return Observable.fromCallable(() -> {
                String strNoSpaces = city.replace(" ", "+");

                String link = "https://api.flickr.com/services/rest/?safe_search=safe&api_key=" +
                        FLIKR_KEY + "&format=json&content_type=1&sort=relevance&method=flickr.photos.search&media=photos&extras="
                        + IMAGE_SIZE + "&per_page=" + IMAGE_COUNT + "&text=" + strNoSpaces + "+" + FLIKR_REQUEST_IMAGE_KEY;

                URL url = new URL(link);
                Timber.d(url.toString());
                String json = core.getResponse(url);

                return getImagesArray(json);
            });

    }


    private String getImagesArray(String json) {
        ArrayList<String> links = new ArrayList<>();
        String[] lines = json.split(",");
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("\"" + IMAGE_SIZE + "\":")) {
                String[] tempString = lines[i].split("\"" + IMAGE_SIZE + "\":");
                String tmp = tempString[1];
                tmp = tmp.replace("\"", "");
                links.add(tmp);
            }
        }
        int id = (int) (Math.random() * IMAGE_COUNT);
        Timber.d("Array links parsing complete");
        return links.get(id).replace("\\", "");
    }

}
