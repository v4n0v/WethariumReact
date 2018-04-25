package com.example.v4n0v.wethariumreact.dagger.modules;

import com.example.v4n0v.wethariumreact.App;

import dagger.Module;
import dagger.Provides;

/**
 * Created by v4n0v on 22.04.18.
 */
@Module
public class AppModule {
    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    App app(){
        return app;
    }
}
