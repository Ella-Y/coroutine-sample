package com.yeji.network

import com.yeji.network.response.ResWeatherCurrent
import com.yeji.network.response.ResWeatherForecastHour
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiWeather {

    @GET("https://api.openweathermap.org/data/2.5/weather?units=metric&lang=en")
    suspend fun getWeatherCurrent(
        @Query("lat") latitude: Double?,
        @Query("lon") longitude: Double?,
        @Query("appid") key: String,
    ): ResWeatherCurrent?

    @GET("https://api.openweathermap.org/data/2.5/forecast?units=metric")
    suspend fun getForecast(
        @Query("lat") latitude: Double?,
        @Query("lon") longitude: Double?,
        @Query("appid") key: String,
    ): ResWeatherForecastHour?

}