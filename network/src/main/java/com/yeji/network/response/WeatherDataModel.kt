package com.yeji.network.response


import com.google.gson.annotations.SerializedName

class ResWeather {
    @SerializedName("id")
    var id = 0

    @SerializedName("main")
    var main: String? = null

    @SerializedName("description")
    var description:String = ""
}


class ResWeatherCoord {
    @SerializedName("lat")
    var lat = 0.0

    @SerializedName("lon")
    var lon = 0.0
}

class ResWeatherCity {
    @SerializedName("id")
    var id = 0

    @SerializedName("name")
    var name: String? = null

    /* @SerializedName("coord")
    var coord: Coord? = null */

    @SerializedName("country")
    var country: String? = null

    @SerializedName("population")
    var population = 0

    @SerializedName("timezone")
    var timezone = 0

    @SerializedName("sunrise")
    var sunrise = 0

    @SerializedName("sunset")
    var sunset = 0
}

/**
 * 온도 정보
 */
class ResWeatherTempMain {
    @SerializedName("temp")
    var temp = 0.0

    @SerializedName("temp_min")
    var tempMin = 0.0

    @SerializedName("temp_max")
    var tempMax = 0.0
}

class ResWeatherTempDay {
    @SerializedName("min")
    var tempMin = 0.0

    @SerializedName("max")
    var tempMax = 0.0

    var tempCur = 0.0
}