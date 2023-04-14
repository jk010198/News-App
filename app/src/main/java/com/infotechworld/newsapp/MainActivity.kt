package com.infotechworld.newsapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.infotechworld.newsapp.adapter.NewsAdapter
import com.infotechworld.newsapp.databinding.ActivityMainBinding
import com.infotechworld.newsapp.model.Article
import com.infotechworld.newsapp.model.NewsData
import com.infotechworld.newsapp.utill.NetworkResult
import com.infotechworld.newsapp.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }

        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        newsViewModel.getD("in", "7c15f07713da4964a1a205a064b0ed65")

        newsViewModel.newsData.observe(this, Observer {
            binding.progressBar.visibility = View.GONE
            when (it) {
                is NetworkResult.Error -> {
                    binding.tvErrorMsg.text = it.message
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is NetworkResult.Sucess -> {
                    val newsAdapter = NewsAdapter(it, this) {
                        click(it)
                    }
                    binding.rv.adapter = newsAdapter
                }
            }
        })
    }

    private fun click(data: Article?) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse("${data?.url}")
        startActivity(openURL)
    }
}