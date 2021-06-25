package com.app.lets_track_test.news

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.app.lets_track_test.R
import com.app.lets_track_test.databinding.ItemNewsBinding
import com.app.lets_track_test.news.models.ArticlesItem
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(private val newsList : ArrayList<ArticlesItem>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder private constructor(private val binding: ItemNewsBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(articlesItem: ArticlesItem) {
            binding.model=articlesItem
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): NewsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemNewsBinding.inflate(layoutInflater, parent, false)

                return NewsViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = newsList[position]
        holder.bind(item)
        holder.itemView.cv_news.animation=AnimationUtils.loadAnimation(holder.itemView.context, R.anim.translated)

    }

}