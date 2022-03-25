package com.example.imagelistview.view.data

interface ImageListRepository {
    suspend fun postLoadingTimes(loadingTimes: List<Long>?) : Boolean
}