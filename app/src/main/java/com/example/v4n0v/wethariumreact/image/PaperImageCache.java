package com.example.v4n0v.wethariumreact.image;

import android.graphics.Bitmap;
import android.os.Environment;

import com.example.v4n0v.wethariumreact.App;

import java.io.File;
import java.io.FileOutputStream;

import io.paperdb.Paper;
import timber.log.Timber;

/**
 * Created by v4n0v on 22.04.18.
 */

public class PaperImageCache implements ICacheImage {
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


        Paper.book("images").delete(city);
        Paper.book("images").write(city, imageFile);
        Timber.d("paper saved image: "+imageFile);
    }

    @Override
    public File readFromCache(String city) {
        File file = Paper.book("images").read(city);
        Timber.d("paper loaded image: "+file);
        return file;
    }
}
