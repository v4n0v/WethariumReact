package com.example.v4n0v.wethariumreact.image;


import com.example.v4n0v.wethariumreact.entities.realm.RealmImage;

import java.io.File;

import io.realm.Realm;
import timber.log.Timber;

public class RealmImageCache implements ICacheImage {
    @Override
    public void writeToCache(File file, String city) {
        Realm realm = Realm.getDefaultInstance();
        RealmImage realmImage = realm.where(RealmImage.class).equalTo("city", city).findFirst();
        if (realmImage != null) {
            realm.executeTransaction(realm1 ->
                    realmImage.setPath(file.getAbsolutePath())
            );
        } else {
            realm.executeTransaction(realm1 -> {
                RealmImage newRealmImage = realm.createObject(RealmImage.class, city);
                newRealmImage.setPath(file.getAbsolutePath());
            });
        }
        Timber.d("realm saved image: " + file);
    }

    @Override
    public File readFromCache(String city) {
        Realm realm = Realm.getDefaultInstance();
        RealmImage realmImage = realm.where(RealmImage.class).equalTo("city", city).findFirst();
        if (realmImage != null) {
            File file = new File(realmImage.getPath());
            Timber.d("realm saved image: " + file);
            return file;
        }
        return null;
    }


}
