package com.demo.wallpaperapi


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("photos")
    val photos: ArrayList<Photo>,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("total_photos")
    val totalPhotos: Int
)