package com.androiddevs.abhidejtestnews.api

import com.androiddevs.abhidejtestnews.models.NewsResponse
import com.androiddevs.abhidejtestnews.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsFeedService {

    @GET("v2/top-headlines")
    suspend fun getNewsFeed(
        @Query("country")
        countryCode: String = "th",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>
}