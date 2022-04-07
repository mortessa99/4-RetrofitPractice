package com.example.a4_retrofitpractice.server

import com.example.a4_retrofitpractice.utils.Constants
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

class ApiClient {
    private lateinit var retrofit: Retrofit
    private val addMoreSettingByOkHttp = OkHttpClient.Builder()
                                        .connectTimeout(60,TimeUnit.SECONDS)
                                        .readTimeout(60,TimeUnit.SECONDS)
                                        .writeTimeout(60,TimeUnit.SECONDS)
                                        .build()

    fun getClient():Retrofit{
        retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(addMoreSettingByOkHttp)
            .build()

        return retrofit
    }
}