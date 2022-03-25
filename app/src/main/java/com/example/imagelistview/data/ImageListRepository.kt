package com.example.imagelistview.data

interface ImageListRepository {
    suspend fun postLoadingTimes(loadingTimes: List<Long>?) : Boolean
}