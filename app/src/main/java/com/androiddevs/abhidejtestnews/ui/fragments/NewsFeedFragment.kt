package com.androiddevs.abhidejtestnews.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddevs.abhidejtestnews.R
import com.androiddevs.abhidejtestnews.adapters.NewsFeedAdapter
import com.androiddevs.abhidejtestnews.listener.NewsFeedItemClickListener
import com.androiddevs.abhidejtestnews.models.Article
import com.androiddevs.abhidejtestnews.ui.DetailActivity
import com.androiddevs.abhidejtestnews.ui.NewsFeedActivity
import com.androiddevs.abhidejtestnews.ui.NewsFeedViewModel
import com.androiddevs.abhidejtestnews.util.Resource
import kotlinx.android.synthetic.main.fragment_news_feed.*

class NewsFeedFragment : NewsFeedItemClickListener,Fragment(R.layout.fragment_news_feed) {

    lateinit var viewModel: NewsFeedViewModel
    lateinit var newsAdapter: NewsFeedAdapter

    val TAG = "BreakingNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsFeedActivity).viewModel
        setupRecyclerView()

        viewModel.newsFeed.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        refreshLayout.setColorSchemeResources(R.color.colorRed)
        refreshLayout.setOnRefreshListener{
            refresh()
        }
    }

    private fun refresh() {
        viewModel.page = 1
        Handler(Looper.getMainLooper()).postDelayed({
            refreshLayout.isRefreshing = false
        },1000)
    }

    private fun hideProgressBar() {
        ProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        ProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsFeedAdapter(this)
        rvNewsFeed.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onNewsFeedItemClicked(item: Article) {
        super.onNewsFeedItemClicked(item)
        val intent = context?.let { DetailActivity.newIntent(it,item.title, item.description, item.urlToImage) }
        startActivity(intent)
    }

    companion object {
        fun newInstance(): NewsFeedFragment {
            val fragment = NewsFeedFragment()
            return fragment
        }
    }
}