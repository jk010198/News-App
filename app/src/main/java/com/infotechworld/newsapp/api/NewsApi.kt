package com.infotechworld.newsapp.api

import com.infotechworld.newsapp.model.NewsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    //top-headlines?country=in&apiKey=7c15f07713da4964a1a205a064b0ed65
    @GET("top-headlines")
    //suspend fun getNews(@Query("country") country: String, @Query("apiKey") key: String): Response<List<NewsData>>
    suspend fun getNews(@Query("country") country: String, @Query("apiKey") key: String): Response<NewsData>
}