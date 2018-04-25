package com.example.v4n0v.wethariumreact.dagger.modules;

import android.widget.ImageView;

import com.example.v4n0v.wethariumreact.image.FlickerLinkLoader;
import com.example.v4n0v.wethariumreact.image.GlideLoader;
import com.example.v4n0v.wethariumreact.image.ICacheImage;
import com.example.v4n0v.wethariumreact.image.IImageLoader;
import com.example.v4n0v.wethariumreact.image.PaperImageCache;
import com.example.v4n0v.wethariumreact.image.RealmImageCache;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module(includes = {FlickerModule.class})
public class ImageModule {
    @Singleton
    @Provides
    public IImageLoader<ImageView> glideLoader(@Named("realm") ICacheImage cacheImage, FlickerLinkLoader flickerLinkLoader) {
        return new GlideLoader(cacheImage, flickerLinkLoader);
    }

    @Provides
    @Named("paper")
    ICacheImage paperCache() {
        return new PaperImageCache();
    }

    @Provides
    @Named("realm")
    ICacheImage realmCache() {
        return new RealmImageCache();
    }

}
