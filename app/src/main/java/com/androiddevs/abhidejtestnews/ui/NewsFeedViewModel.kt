package com.androiddevs.abhidejtestnews.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.abhidejtestnews.models.NewsResponse
import com.androiddevs.abhidejtestnews.repository.NewsFeedRepository
import com.androiddevs.abhidejtestnews.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsFeedViewModel(
    val newsRepository: NewsFeedRepository
) : ViewModel() {

    val newsFeed: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var page = 1

    init {
        getNewsFeed("th")
    }

    fun getNewsFeed(countryCode: String) = viewModelScope.launch {
        newsFeed.postValue(Resource.Loading())
        val response = newsRepository.getNewsFeed(countryCode, page)
        newsFeed.postValue(NewsFeedResponse(response))
    }

    private fun NewsFeedResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}