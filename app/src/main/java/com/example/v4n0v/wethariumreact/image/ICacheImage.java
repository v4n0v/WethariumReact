package com.example.v4n0v.wethariumreact.image;

import java.io.File;

public interface ICacheImage {

    void writeToCache(File file, String city);
    File readFromCache(String city);

}
