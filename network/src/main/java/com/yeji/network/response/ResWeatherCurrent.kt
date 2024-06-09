package com.yeji.network.response


import com.google.gson.annotations.SerializedName


class ResWeatherCurrent {
    @SerializedName("cod")
    var cod = 0

    @SerializedName("coord")
    var coord: ResWeatherCoord? = null

    @SerializedName("weather")
    var weather: ArrayList<ResWeather> = arrayListOf()

    @SerializedName("base")
    var base: String? = null

    @SerializedName("main")
    var main: ResWeatherTempMain? = null

    @SerializedName("visibility")
    var visibility = 0

    @SerializedName("dt")
    var dt:Long = 0

    @SerializedName("timezone")
    var timezone = 0

    @SerializedName("id")
    var id = 0

    @SerializedName("name")
    var name: String? = null


}