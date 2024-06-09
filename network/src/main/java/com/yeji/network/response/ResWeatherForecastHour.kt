package com.yeji.network.response

import com.google.gson.annotations.SerializedName


class ResWeatherForecastHour {
    @SerializedName("cod")
    var cod: String? = null

    @SerializedName("message")
    var message = 0.0

    @SerializedName("cnt")
    var cnt = 0

    @SerializedName("list")
    var list: ArrayList<ListItem> = arrayListOf()

    @SerializedName("city")
    var city: ResWeatherCity? = null


    inner class ListItem {
        @SerializedName("dt")
        var dt:Long = 0

        @SerializedName("main")
        var main: ResWeatherTempMain? = null

        @SerializedName("weather")
        var weather: ArrayList<ResWeather>? = null


        @SerializedName("visibility")
        var visibility = 0

        @SerializedName("pop")
        var pop = 0.0

        @SerializedName("dt_txt")
        var dtTxt: String? = null
    }
}