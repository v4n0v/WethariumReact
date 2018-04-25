package com.example.v4n0v.wethariumreact.dagger;

import com.example.v4n0v.wethariumreact.dagger.modules.AppModule;
import com.example.v4n0v.wethariumreact.dagger.modules.CacheModule;
import com.example.v4n0v.wethariumreact.dagger.modules.ConnectionModule;
import com.example.v4n0v.wethariumreact.dagger.modules.ImageModule;
import com.example.v4n0v.wethariumreact.mvp.model.WeatherHistoryModel;
import com.example.v4n0v.wethariumreact.mvp.presenter.MainPresenter;
import com.example.v4n0v.wethariumreact.mvp.views.activity.MainActivity;
import com.example.v4n0v.wethariumreact.mvp.views.activity.SplashActivity;
import com.example.v4n0v.wethariumreact.service.WeatherService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ConnectionModule.class, ImageModule.class, CacheModule.class})
public interface AppComponent {
    void inject(WeatherService service);
    void inject(MainPresenter presenter);
    void inject(MainActivity mainActivity);
    void inject(SplashActivity splashActivity);
    void inject(WeatherHistoryModel weatherHistoryModel);
}
