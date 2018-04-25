package com.example.v4n0v.wethariumreact;

import android.app.Application;

import com.example.v4n0v.wethariumreact.dagger.AppComponent;
import com.example.v4n0v.wethariumreact.dagger.DaggerAppComponent;
import com.example.v4n0v.wethariumreact.dagger.modules.AppModule;

import io.paperdb.Paper;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

public class App extends Application {
    private static App instance;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Timber.plant(new Timber.DebugTree());
        Paper.init(this);
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);

        appComponent= DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static App getInstance()
    {
        return instance;
    }
}
