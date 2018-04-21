package com.example.v4n0v.wethariumreact.entities;


public class WeatherInfo {
    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    private Coord coord;


//    private int tempMin;
//    private int tempMax;
////    private boolean isPressure;
////    private boolean isHumidity;
////    private boolean isWind;
//
    private int temp;
//    private  int hum;
//    private  int speed;
//    private  int pressure;
//    private int id;
//
//    private String description;
//    private String additionalInfo;
//    private String name;
//
//    public int getTempMin() {
//        return tempMin;
//    }
//
//    public void setTempMin(int tempMin) {
//        this.tempMin = tempMin;
//    }
////    public WeatherInfo(){
////
////    }
//
//
//    public int getTempMax() {
//        return tempMax;
//    }
//
//    public void setTempMax(int tempMax) {
//        this.tempMax = tempMax;
//    }
//
////    public boolean isPressure() {
////        return isPressure;
////    }
////
////    public void setPressure(boolean pressure) {
////        isPressure = pressure;
////    }
////
////    public boolean isHumidity() {
////        return isHumidity;
////    }
////
////    public void setHumidity(boolean humidity) {
////        isHumidity = humidity;
////    }
////
////    public boolean isWind() {
////        return isWind;
////    }
////
////    public void setSpeed(boolean speed) {
////        isWind = speed;
////    }
//
    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
//
//    public int getHum() {
//        return hum;
//    }
//
//    public void setHum(int hum) {
//        this.hum = hum;
//    }
//
//    public int getSpeed() {
//        return speed;
//    }
//
//    public void setSpeed(int speed) {
//        this.speed = speed;
//    }
//
//    public int getPressure() {
//        return pressure;
//    }
//
//    public void setPressure(int pressure) {
//        this.pressure = pressure;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getAdditionalInfo() {
//        return additionalInfo;
//    }
//
//    public void setAdditionalInfo(String additionalInfo) {
//        this.additionalInfo = additionalInfo;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }


    class Coord {
        private float lon;
        private float lat;

        public float getLon() {
            return lon;
        }

        public void setLon(float lon) {
            this.lon = lon;
        }

        public float getLat() {
            return lat;
        }

        public void setLat(float lat) {
            this.lat = lat;
        }
    }

}

