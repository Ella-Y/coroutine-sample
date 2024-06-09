package com.yeji.coroutineSample

import com.yeji.coroutineSample.data.WeatherEnum
import com.yeji.coroutineSample.data.WeatherModel
import com.yeji.network.ApiWeather
import com.yeji.network.NetworkService
import com.yeji.network.response.ResWeather
import com.yeji.network.response.ResWeatherCurrent
import com.yeji.network.response.ResWeatherForecastHour
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Calendar
import kotlin.math.roundToInt

class WeatherUtil {
    private val appId = ""
    private val apiWeather: ApiWeather = NetworkService.apiWeather


    suspend fun getWeather(latitude: Double, longitude: Double): WeatherModel? {
        return withContext(Dispatchers.IO) {
            val deferredCurrent = async { apiWeather.getCurrentCity(latitude, longitude, appId) }
            val deferredForecast = async { apiWeather.getForecast(latitude, longitude, appId) }

            try {
                // 여러 개의 호출을 동시에 수행
                val list: List<Any?> = awaitAll(deferredCurrent, deferredForecast)
                val currentWeather = list[0] as ResWeatherCurrent

                val model = makeWeather(
                    list[0] as ResWeatherCurrent?,
                    list[1] as ResWeatherForecastHour?
                )
                return@withContext model

            } catch (e: Exception) {
                return@withContext null
            }
        }
    }

    private fun makeWeather(
        dayData: ResWeatherCurrent?,
        forecastData: ResWeatherForecastHour?,
    ): WeatherModel {
        val retVal = WeatherModel()
        retVal.city = forecastData?.city?.name ?: ""

        val now = Calendar.getInstance()
        val today = LocalDate.now()
        val tomorrow = today.plusDays(1)
        retVal.todayTime = now.time

        if (dayData != null) {
            // 현재 날씨
            retVal.current.weather = dayData.weather[0].getWeatherEnum()
            retVal.dailyWeather = dayData.weather[0].getWeatherEnum()

            dayData.main!!.let {
                retVal.current.temp = it.temp.roundToInt()
                retVal.maxTemp = it.tempMax.roundToInt()
                retVal.minTemp = it.tempMin.roundToInt()
            }
        }


        // 시간별 날씨
        if (forecastData != null) {
            var i: Int

            for (weatherItem in forecastData.list) {
                val zdt = ZonedDateTime.ofInstant(
                    Instant.ofEpochSecond(weatherItem.dt),
                    ZoneId.of("UTC")
                )

                // 사용자 시간대로 변경
                val localZdt = zdt.withZoneSameInstant(ZoneId.systemDefault())
                if (localZdt.toLocalDate() > tomorrow) {
                    break
                }

                // 오늘
                i = localZdt.hour / 3 // 0,3,6,9,12,15,18,21 시
                val weatherInfo = if (localZdt.toLocalDate() == today) {
                    retVal.today[i]
                }
                // 내일
                else {
                    retVal.tomorrow[i]
                }

                weatherInfo!!.weather = try {
                    weatherItem.weather!![0].getWeatherEnum()
                } catch (ignored: Exception) {
                    WeatherEnum.SUNANDCLOUD.ordinal
                }
                weatherInfo.temp = weatherItem.main!!.temp.toInt()

            }
        }


        return retVal
    }

}

private fun ResWeather.getWeatherEnum():Int{
    val _id = id / 100 * 100
    var retVal = WeatherEnum.SUNANDCLOUD
    when (_id) {
        200 -> {
            retVal = WeatherEnum.CLOUD
            if (this.description.equals(
                    "rain",
                    ignoreCase = true
                ) || this.description.equals("drizzle", ignoreCase = true)
            ) {
                retVal = WeatherEnum.RAIN
            }
        }

        300, 500 -> retVal = WeatherEnum.RAIN
        600 -> retVal = WeatherEnum.SNOW
        700 -> retVal = WeatherEnum.SUNANDCLOUD
        800 -> retVal = when (id) {
            800 -> {
                WeatherEnum.SUN
            }
            801 -> {
                WeatherEnum.SUNANDCLOUD
            }
            else -> {
                WeatherEnum.CLOUD
            }
        }
    }
    return retVal.ordinal
}