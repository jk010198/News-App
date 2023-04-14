package com.infotechworld.newsapp.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infotechworld.newsapp.model.NewsData
import com.infotechworld.newsapp.repository.NewsRepo
import com.infotechworld.newsapp.utill.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repo: NewsRepo): ViewModel() {

    private val _newsData = MutableLiveData<NetworkResult<NewsData>>()
    val newsData: LiveData<NetworkResult<NewsData>>
    get() = _newsData

    fun getD(country: String, key: String) {
        viewModelScope.launch {
            val res = repo.getNews(country, key)
            if(res.isSuccessful){
                _newsData.postValue(NetworkResult.Sucess(res.body()!!))
            } else if (res.errorBody() != null) {
                val obj = JSONObject(res.errorBody()!!.charStream().readText())
                _newsData.postValue(NetworkResult.Error(obj.getString("message")))
            } else {
                _newsData.postValue(NetworkResult.Error("wrong..."))
            }
        }
    }

}