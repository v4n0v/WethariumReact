package com.example.v4n0v.wethariumreact.image;

import android.graphics.Bitmap;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.v4n0v.wethariumreact.common.NetworkStatus;
import com.example.v4n0v.wethariumreact.dagger.modules.FlickerModule;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * класс загузчик списка ссылок на фото из Flicker, поиск по городу
 */
public class GlideLoader implements IImageLoader<ImageView> {

    @Inject
    ICacheImage cacheImage;
    @Inject
    FlickerLinkLoader linkLoader;

    public GlideLoader(ICacheImage cacheImage, FlickerLinkLoader linkLoader) {
        this.cacheImage = cacheImage;
        this.linkLoader = linkLoader;
    }



    @Override
    public void loadInto(String city, ImageView container) {
        File file = cacheImage.readFromCache(city.toLowerCase());
        if (file != null) {
            Timber.d("loading city image from phone memory\n" + file.getAbsolutePath());
            Glide.with(container.getContext())
                    .load(file)
                    .into(container);
        } else {
            downloadPhoto(city, container);
        }

    }

    @Override
    public void downloadPhoto(String city, ImageView container) {
        if (NetworkStatus.isOnline()) {
            linkLoader.getPhotoLinks(city)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(link -> {
                        Timber.d("I've got " + link + " link");

                        GlideApp.with(container.getContext()).asBitmap().load(link).listener(new RequestListener<Bitmap>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                Timber.e("failed to load image", e);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                //
                                Timber.d("saving city image into phone memory " + link);
//                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                                resource.compress(Bitmap.CompressFormat.JPEG, 100, stream);

//                                File file = new File(container.getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), city.toLowerCase() + ".jpg");
//                                if (file.exists()){
//                                    file.delete();
//                                }
//                                try {
//                                    stream.writeTo(new FileOutputStream(file));
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
                                cacheImage.writeToCache(resource, city.toLowerCase());
                                Timber.d(city+ " city image saved in phone memory, path added to realm database");
                                return false;
                            }
                        }).into(container);
                    });

        }
    }


}
