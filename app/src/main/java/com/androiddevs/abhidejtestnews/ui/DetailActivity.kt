package com.androiddevs.abhidejtestnews.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.androiddevs.abhidejtestnews.R
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.Detail)

        val title: String = intent?.extras?.getString(TITLE) ?: ""
        val description: String = intent?.extras?.getString(DESCRIPTION) ?: ""
        val urlImage: String = intent?.extras?.getString(URL_IMAGE) ?: ""

        val image_detail: ImageView = findViewById(R.id.item_gallery_post_image_imageview)
        val text_title: TextView = findViewById(R.id.tvTitle)
        val text_description : TextView = findViewById(R.id.tvDescription)

        if(urlImage.isNotEmpty())
        {
            Glide.with(this)
                .load(urlImage)
                .into(image_detail)
        }

        if(title.isNotEmpty())
        text_title.text = title

        if(description.isNotEmpty())
        text_description.text = description
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val TITLE = "TITLE"
        private const val DESCRIPTION = "DESCRIPTION"
        private const val URL_IMAGE = "URL_IMAGE"

        fun newIntent(context: Context, title: String?, description: String?, urlImage: String?): Intent {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra(TITLE, title)
                putExtra(DESCRIPTION, description)
                putExtra(URL_IMAGE, urlImage)
            }
            return intent
        }
    }

}