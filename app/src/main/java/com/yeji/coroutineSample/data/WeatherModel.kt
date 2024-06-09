package com.yeji.coroutineSample.data

import com.google.gson.annotations.SerializedName
import java.util.Date


class WeatherModel {
    @SerializedName("city")
    var city: String? = null

    @SerializedName("today")
    var today: Array<WeatherInfo?>

    @SerializedName("tomorrow")
    var tomorrow: Array<WeatherInfo?>

    @SerializedName("current")
    var current: WeatherInfo = WeatherInfo()

    @SerializedName("todayTime")
    var todayTime: Date? = null

    @SerializedName("dailyWeather")
    var dailyWeather = 0

    @SerializedName("maxTemp")
    var maxTemp = 0

    @SerializedName("minTemp")
    var minTemp = 0

    init {
        today = arrayOfNulls<WeatherInfo>(8)
        tomorrow = arrayOfNulls<WeatherInfo>(8)
        for (i in today.indices) {
            today[i] = WeatherInfo()
            tomorrow[i] = WeatherInfo()
        }
    }

    override fun toString(): String {
        return "WeatherModel(city=$city, today=${today.contentToString()}, tomorrow=${tomorrow.contentToString()}, current=$current, todayTime=$todayTime, dailyWeather=$dailyWeather, maxTemp=$maxTemp, minTemp=$minTemp)"
    }

}