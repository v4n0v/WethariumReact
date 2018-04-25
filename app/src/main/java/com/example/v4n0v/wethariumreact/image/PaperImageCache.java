package com.example.v4n0v.wethariumreact.image;

import java.io.File;

import io.paperdb.Paper;
import timber.log.Timber;

/**
 * Created by v4n0v on 22.04.18.
 */

public class PaperImageCache implements ICacheImage {
    @Override
    public void writeToCache(File file, String city) {
        Paper.book("images").delete(city);
        Paper.book("images").write(city, file);
        Timber.d("paper saved image: "+file);
    }

    @Override
    public File readFromCache(String city) {
        File file = Paper.book("images").read(city);
        Timber.d("paper loaded image: "+file);
        return file;
    }
}
