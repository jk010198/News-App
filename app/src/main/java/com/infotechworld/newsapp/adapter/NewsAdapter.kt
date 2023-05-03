package com.infotechworld.newsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.infotechworld.newsapp.databinding.ItemLayoutBinding
import com.infotechworld.newsapp.model.Article
import com.infotechworld.newsapp.model.NewsData
import com.infotechworld.newsapp.utill.NetworkResult

class NewsAdapter(val list: NetworkResult<NewsData>, val context: Context, val click: ((Article?) -> Unit)) :
    RecyclerView.Adapter<NewsAdapter.MyHolder>() {
    class MyHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.binding.apply {
            with(list.data?.articles?.get(position)) {
                news = this

                //tvTitle.text = "Title :- ${this?.title}"
                if (this?.description.equals(null)) {
                    tvDesc.visibility = View.GONE
                } else {
                    tvDesc.text = "Desc :- ${this?.description}"
                }
                tvAuthor.text = "Author :- ${this?.author}"
                tvDateTime.text = "Posted At :- ${this?.publishedAt}"
                if (this?.urlToImage.equals(null)) {

                } else {
                    Glide.with(context).load(this?.urlToImage).into(imageView)
                }

            }
        }

        holder.binding.cardView.setOnClickListener {
            click(list.data?.articles?.get(position))
        }
    }

    override fun getItemCount(): Int {
        return list.data?.articles?.size!!
    }
}