package com.example.v4n0v.wethariumreact.entities.gson;


public class Weather {

    private int humidity;
    private String city;
    private int temperature;
    private int wind;
    private int pressure;
    private int tempMin;
    private int tempMax;
    private String description;
    private String mainInfo;
    private int id;
private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

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


    public Weather() {
    }

    public Weather(String city, int temperature, String description,
                   int wind, int pressure, int humidity,
                   int id, int tempMin, int tempMax,
                   long time) {
        this.city = city;
        this.temperature = temperature;
        this.wind = wind;
        this.pressure = pressure;
        this.humidity = humidity;
        this.id = id;
        this.description = description;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.time = time;
    }
}
