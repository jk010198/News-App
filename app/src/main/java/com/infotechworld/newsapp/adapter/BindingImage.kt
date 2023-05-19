package com.infotechworld.newsapp.adapter

import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.infotechworld.newsapp.R

@BindingAdapter("bindImg")
fun ImageView.bindImg(url: String){
    if(url.equals("null")){
        Glide.with(this.context).load(R.drawable.newslogo).into(this)
    } else {
        Glide.with(this.context).load(url).into(this)
    }
}
//bindImg="@{news.urlToImage != null ? news.urlToImage : `@drawable/newslogo`}"