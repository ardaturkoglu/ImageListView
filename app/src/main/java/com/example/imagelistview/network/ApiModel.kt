package com.example.imagelistview.network

import retrofit2.Response
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiModel {

    @POST("post")
    fun postLoadingTimes(@Body loadingTimes: List<Int>): Response<ResponseBody>

    companion object {
        const val BASE_URL = "https://httpbin.org/"
    }
}