package com.example.v4n0v.wethariumreact.entities;

/**
 * Created by v4n0v on 21.04.18.
 */

public class WeatherBuilder {
    private int tempMin;
    private int tempMax;
    private boolean isPressure;
    private boolean isHumidity;
    private boolean isWind;

    private int temp;
    private int hum;
    private int wind;
    private int press;
    private String decription;
    private String additionalInfo;
    private int weatherId;
    private String city;

    public WeatherBuilder setTempMin(int tempMin) {
        this.tempMin = tempMin;
        return this;
    }

    public WeatherBuilder setTempMax(int tempMax) {
        this.tempMax = tempMax;
        return this;
    }

    public WeatherBuilder setPressure(boolean pressure) {
        isPressure = pressure;
        return this;
    }

    public WeatherBuilder setHumidity(boolean humidity) {
        isHumidity = humidity;
        return this;
    }

    public WeatherBuilder setWind(boolean wind) {
        isWind = wind;
        return this;
    }

    public WeatherBuilder setTemp(int temp) {
        this.temp = temp;
        return this;
    }

    public WeatherBuilder setHum(int hum) {
        this.hum = hum;
        return this;
    }

    public WeatherBuilder setWind(int wind) {
        this.wind = wind;
        return this;
    }

    public WeatherBuilder setPress(int press) {
        this.press = press;
        return this;
    }

    public WeatherBuilder setDecription(String decription) {
        this.decription = decription;
        return this;
    }

    public WeatherBuilder setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    public WeatherBuilder setWeatherId(int weatherId) {
        this.weatherId = weatherId;
        return this;
    }

    public WeatherBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public WeatherInfo build(){
//        WeatherInfo weather = new WeatherInfo();
//        weather.setName(city);
//        weather.setAdditionalInfo(additionalInfo);
//        weather.setDescription(decription);
//        weather.setHum(hum);
//      //  weather.setHumidity(isHumidity);
//        weather.setPressure(press);
//      //  weather.setPressure(isPressure);
//        weather.setId(weatherId);
//        weather.setSpeed(wind);
//       // weather.setSpeed(isWind);
//        weather.setTempMin(tempMin);
//        weather.setTemp(temp);
//        weather.setTempMax(tempMax);
//
//        return weather;
        return null;
    }
}
