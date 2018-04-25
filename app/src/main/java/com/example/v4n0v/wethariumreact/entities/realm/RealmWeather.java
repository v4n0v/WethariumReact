package com.example.v4n0v.wethariumreact.entities.realm;

import com.example.v4n0v.wethariumreact.entities.gson.Weather;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by v4n0v on 24.04.18.
 */

public class RealmWeather extends RealmObject {
    @PrimaryKey
    private String city;
    private int temperature;
    private int wind;
    private int pressure;
    private int tempMin;
    private int tempMax;
    private String description;
    private String mainInfo;
    private int id;
    private int humidity;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    private long time;
    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    private float lat;

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    private float lon;

    public void setCity(String city) {
        this.city = city;
    }


    public void setWind(int wind) {
        this.wind = wind;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }


    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getCity() {
        return city;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getWind() {
        return wind;
    }

    public int getId() {
        return id;
    }


    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }


    public int getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }


    public String getMainInfo() {
        return mainInfo;
    }

    public void setMainInfo(String mainInfo) {
        this.mainInfo = mainInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setId(int id) {
        this.id = id;
    }


}
