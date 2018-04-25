package com.example.v4n0v.wethariumreact.dagger.modules;

import com.example.v4n0v.wethariumreact.image.FlickerLinkLoader;
import com.example.v4n0v.wethariumreact.okApi.ConnectionCore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module(includes = {ConnectionModule.class})
public class FlickerModule {

    @Provides
    FlickerLinkLoader flickerLinkLoader(ConnectionCore core){
        return new FlickerLinkLoader(core);
    }
}
