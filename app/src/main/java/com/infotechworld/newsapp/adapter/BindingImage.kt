package com.infotechworld.newsapp.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bindImg")
fun ImageView.bindImg(url: String){
    Glide.with(this.context).load(url).into(this)
}