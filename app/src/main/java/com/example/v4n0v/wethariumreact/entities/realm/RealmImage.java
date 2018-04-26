package com.example.v4n0v.wethariumreact.entities.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class RealmImage extends RealmObject {
    @PrimaryKey
    private String city;
    private String path;



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
