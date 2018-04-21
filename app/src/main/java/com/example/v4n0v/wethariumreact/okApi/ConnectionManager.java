package com.example.v4n0v.wethariumreact.okApi;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by v4n0v on 22.04.18.
 */

public class ConnectionManager {

    private static ConnectionManager instance;

    public static ConnectionManager getConnection() {
        if (instance==null) return new ConnectionManager();
        return instance;
    }


    public static Single<String> getWeather(String city) {
        return Single.fromCallable(() -> {
            OkHttpClient client = new OkHttpClient();
            Request req = new Request.Builder()
                    .url("http://api.openweathermap.org/data/2.5/weather?q="+city+"&units=metric&appid=1aa546d01134ed09d869b84c7e83e34f")
                    .build();
            return client.newCall(req).execute().body().string();
        });
    }

}
