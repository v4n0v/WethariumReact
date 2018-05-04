package com.example.v4n0v.wethariumreact.image;


import android.graphics.Bitmap;
import android.os.Environment;

import com.example.v4n0v.wethariumreact.App;
import com.example.v4n0v.wethariumreact.entities.realm.RealmImage;
import java.io.File;
import java.io.FileOutputStream;

import io.realm.Realm;
import timber.log.Timber;

public class RealmImageCache implements ICacheImage {
    @Override
    public void writeToCache(Bitmap bitmap, String city) {
        final File imageFile = new File(App.getInstance().getExternalFilesDir(Environment.DIRECTORY_PICTURES), city+".jpg");
        FileOutputStream fos;

        try
        {
            fos = new FileOutputStream(imageFile);
            bitmap.compress(  Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
        }
        catch (Exception e)
        {
            Timber.d("Failed to save image");

        }

        Realm realm = Realm.getDefaultInstance();
        RealmImage realmImage = realm.where(RealmImage.class).equalTo("city", city).findFirst();
        if (realmImage != null) {
            realm.executeTransaction(realm1 ->
                    realmImage.setPath(imageFile.getAbsolutePath())
            );
        } else {
            realm.executeTransaction(realm1 -> {
                RealmImage newRealmImage = realm.createObject(RealmImage.class, city);
                newRealmImage.setPath(imageFile.getAbsolutePath());
            });
        }
        Timber.d("realm saved image: " + imageFile);
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
