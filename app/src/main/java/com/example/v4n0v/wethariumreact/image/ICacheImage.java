package com.example.v4n0v.wethariumreact.image;

import android.graphics.Bitmap;

import java.io.File;

public interface ICacheImage {

    void writeToCache(Bitmap file, String city);
    File readFromCache(String city);

}
