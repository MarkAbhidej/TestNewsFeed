package com.androiddevs.abhidejtestnews.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androiddevs.abhidejtestnews.repository.NewsFeedRepository

class NewsViewModelProviderFactory(
    val newsRepository: NewsFeedRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsFeedViewModel(newsRepository) as T
    }
}