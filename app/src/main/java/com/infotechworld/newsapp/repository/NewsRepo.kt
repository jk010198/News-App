package com.infotechworld.newsapp.repository

import com.infotechworld.newsapp.api.NewsApi
import com.infotechworld.newsapp.model.NewsData
import retrofit2.Response
import javax.inject.Inject

class NewsRepo @Inject constructor(private val api: NewsApi) {

//    suspend fun getNews(country: String, key: String): Response<NewsData>{
//       return api.getNews(country, key)
//    }

    suspend fun getNews(country: String, key: String) = api.getNews(country, key)
}