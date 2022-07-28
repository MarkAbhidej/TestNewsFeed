package com.androiddevs.abhidejtestnews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.androiddevs.abhidejtestnews.R
import com.androiddevs.abhidejtestnews.repository.NewsFeedRepository
import com.androiddevs.abhidejtestnews.ui.fragments.NewsFeedFragment

class NewsFeedActivity : AppCompatActivity() {

    lateinit var viewModel: NewsFeedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        supportActionBar?.setTitle(R.string.News)

        val newsFeedRepository = NewsFeedRepository()
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsFeedRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsFeedViewModel::class.java)
        loadFragment()
    }

    private fun loadFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = NewsFeedFragment.newInstance()
        fragmentTransaction.replace(R.id.newsHomeFragment, fragment)
        fragmentTransaction.commit()
    }
}
