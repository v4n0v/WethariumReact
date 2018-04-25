package com.example.v4n0v.wethariumreact.entities.realm;

import com.example.v4n0v.wethariumreact.entities.gson.Weather;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by v4n0v on 25.04.18.
 */

public class RealmWeatherHistory extends RealmObject {

    @PrimaryKey
    private String listName;
    private RealmList<RealmWeather> weathers = new RealmList<>();


    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }


    public RealmList<RealmWeather> getWeathers() {
        return weathers;
    }

    public void setWeathers(RealmList<RealmWeather> weathers) {
        this.weathers = weathers;
    }
}
