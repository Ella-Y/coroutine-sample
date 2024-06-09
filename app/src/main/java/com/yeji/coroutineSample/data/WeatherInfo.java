package com.yeji.coroutineSample.data;


import androidx.annotation.NonNull;

public class WeatherInfo {
    private int weather;
    private int temp;

    public WeatherInfo() {
    }

    public WeatherInfo(WeatherEnum weather, int temp) {
        this.weather = weather.ordinal();
        this.temp = temp;
    }

    public int getWeather() {
        return this.weather;
    }

    public void setWeather(int weather) {
        this.weather = weather;
    }

    public int getTemp() {
        return this.temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    @NonNull
    public String toString() {
        return "WeatherInfo{weather=" + this.weather + ", temp=" + this.temp + '}';
    }
}
