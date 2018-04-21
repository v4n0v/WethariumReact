package com.example.v4n0v.wethariumreact;

import android.app.Application;

import io.paperdb.Paper;
import timber.log.Timber;

/**
 * Created by v4n0v on 21.04.18.
 */

public class App extends Application {
    static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Timber.plant(new Timber.DebugTree());
        Paper.init(this);
    }

    public static App getInstance() {
        return app;
    }
}
