package com.example.v4n0v.wethariumreact.okApi;

import java.net.URL;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ConnectionManager {
    private static final String OPEN_API_MAP = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";
    private static final String OPEN_API_KEY = "1aa546d01134ed09d869b84c7e83e34f";
    private static ConnectionManager instance;

    public static ConnectionManager getConnection() {
        if (instance == null) return new ConnectionManager();
        return instance;
    }


    public static Single<String> getWeather(String city) {
        return Single.fromCallable(() -> {
            OkHttpClient client = new OkHttpClient();
            URL url = new URL(String.format(OPEN_API_MAP, city) + "&appid=" + OPEN_API_KEY);

            Request req = new Request.Builder()
                    .url(url)
                    .build();
            return client.newCall(req).execute().body().string();
        });
    }

}
