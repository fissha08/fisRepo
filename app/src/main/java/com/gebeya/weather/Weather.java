package com.gebeya.weather;

public class Weather {

    public double longtiude, latitude;
    public String cityName;
    public int temprature;

    public Weather(double longtiude, double latitude, String cityName, int temprature) {
        this.longtiude = longtiude;
        this.latitude = latitude;
        this.cityName = cityName;
        this.temprature = temprature;
    }

    public double getLongtiude() {
        return longtiude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getCityName() {
        return cityName;
    }

    public int getTemprature() {
        return temprature;
    }
}
