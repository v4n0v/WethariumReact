package com.example.v4n0v.wethariumreact.image;

public interface IImageLoader<T> {
    void loadInto(String city, T container);
    void downloadPhoto(String city, T container);
}
