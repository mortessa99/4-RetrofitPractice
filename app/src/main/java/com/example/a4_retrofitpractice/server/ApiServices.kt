package com.example.a4_retrofitpractice.server

import com.example.a4_retrofitpractice.model.ResponseMoviesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("movies")
    fun getMoviesList(@Query("page") setPage:Int):Call<ResponseMoviesList>
}