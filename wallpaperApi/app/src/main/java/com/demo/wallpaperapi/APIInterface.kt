package com.demo.wallpaperapi

import com.demo.wallpaperapi.model.Response
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET("photos")
    fun getData(): Call<Response>
}