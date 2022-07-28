package com.androiddevs.abhidejtestnews.listener

import com.androiddevs.abhidejtestnews.models.Article

interface NewsFeedItemClickListener {

    fun onNewsFeedItemClicked(item: Article) {

    }
}