package com.yeji.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object NetworkService {
    private var API_URL: String = ""
    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun getRetroFit(url: String): Retrofit {
        val clientBuilder = OkHttpClient.Builder()
        setOkHttpClient(clientBuilder)


        return Retrofit.Builder()
            .client(clientBuilder.build())
            .baseUrl(url)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(gsonFactory())
            .build()
    }

    private fun gsonFactory(): GsonConverterFactory {
        val gson = GsonBuilder().setLenient().create()
        return GsonConverterFactory.create(gson)
    }

    private fun setOkHttpClient(builder: OkHttpClient.Builder) {

        builder.connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(15, TimeUnit.SECONDS)

//        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpLoggingInterceptor)
//        }

        builder.build()
    }

    private fun <T> buildService(service: Class<T>, url: String = API_URL): T {
        return getRetroFit(url).create(service)
    }

    // services 등록
    var apiWeather: ApiWeather = buildService(ApiWeather::class.java)

}