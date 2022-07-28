package com.androiddevs.abhidejtestnews.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.abhidejtestnews.R
import com.androiddevs.abhidejtestnews.listener.NewsFeedItemClickListener
import com.androiddevs.abhidejtestnews.models.Article
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_news_feed.view.*

class NewsFeedAdapter(private val listener: NewsFeedItemClickListener) : RecyclerView.Adapter<NewsFeedAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_news_feed,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(ivNewsImage)
            tvTitle.text = article.title
            tvDescription.text = article.description
            val text_updated = this.context.getString(R.string.Updated) + " " + article.publishedAt
            tvUpdated.text = text_updated

            setOnClickListener {
                listener.onNewsFeedItemClicked(article)
            }
        }
    }

}













