package com.example.v4n0v.wethariumreact.api;


import com.example.v4n0v.wethariumreact.gson.Weather;
import com.example.v4n0v.wethariumreact.gson.WeatherDeserializer;
import com.example.v4n0v.wethariumreact.gson.WeatherMain;
import com.example.v4n0v.wethariumreact.gson.WeatherMainDeserializer;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class ApiHolder {
// http://api.openweathermap.org/data/2.5/weather?q=Moscow&units=metric&appid=1aa546d01134ed09d869b84c7e83e34f
//    private static final String OPEN_API_MAP = "http://api.openweathermap.org/data/2.5/weather?q=Moscow&units=metric&appid=1aa546d01134ed09d869b84c7e83e34f/";
    private static final String OPEN_API_MAP = "http://api.openweathermap.org/data/2.5/";

    private static ApiHolder instance = new ApiHolder();

    private static ApiHolder getInstance() {
        if (instance == null) {
            return new ApiHolder();
        } else {
            return instance;
        }
    }

    public static ApiService getApi() {
        return getInstance().api;
    }

    private ApiService api;

    private ApiHolder() {
//
//        Gson gson = new GsonBuilder()
//                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//                .create();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Weather.class, new WeatherDeserializer())
                .registerTypeAdapter(WeatherMain.class, new WeatherMainDeserializer())
                .create();


        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(chain -> {
            Request originalRequest = chain.request();
            Timber.d(originalRequest.body().toString());
            return chain.proceed(originalRequest);
        }).build();


        api = new Retrofit.Builder()
                .baseUrl(OPEN_API_MAP)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ApiService.class);

    }

}
