package com.domain.task.news.data.remote

import com.domain.task.core.data.models.BaseResponse
import com.domain.task.news.data.models.News
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {

    @GET("/svc/topstories/v2/home.json")
    suspend fun fetchAllNews(): Response<BaseResponse<List<News>>>



}