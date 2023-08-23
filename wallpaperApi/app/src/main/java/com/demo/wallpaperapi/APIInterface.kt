package com.demo.wallpaperapi

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET("photos")
    fun getData(): Call<Response>
}