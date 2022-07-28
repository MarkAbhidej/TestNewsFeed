package com.androiddevs.abhidejtestnews.repository

import com.androiddevs.abhidejtestnews.api.RetrofitInstance

class NewsFeedRepository() {
    suspend fun getNewsFeed(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getNewsFeed(countryCode, pageNumber)
}