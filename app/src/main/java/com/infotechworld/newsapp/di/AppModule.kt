package com.infotechworld.newsapp.di

import com.infotechworld.newsapp.api.NewsApi
import com.infotechworld.newsapp.utill.Constance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun retrofitInstance(): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constance.BASEURL)
            .build()
    }

    @Provides
    @Singleton
    fun apiInstance(retrofit: Retrofit): NewsApi{
        return retrofit.create(NewsApi::class.java)
    }
}