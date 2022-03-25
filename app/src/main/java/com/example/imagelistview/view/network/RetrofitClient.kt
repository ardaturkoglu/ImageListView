package com.example.imagelistview.view.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {
    private val myApi: ApiModel

    init {
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .build()
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(ApiModel.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        myApi = retrofit.create(ApiModel::class.java)
    }

    fun getMyApi(): ApiModel {
        return myApi
    }
}