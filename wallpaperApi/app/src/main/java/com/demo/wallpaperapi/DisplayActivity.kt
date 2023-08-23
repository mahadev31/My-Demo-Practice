package com.demo.wallpaperapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.demo.wallpaperapi.databinding.ActivityDisplayBinding

class DisplayActivity : AppCompatActivity() {
    lateinit var displayBinding: ActivityDisplayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        displayBinding = ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(displayBinding.root)

        initView()
    }

    private fun initView() {
        var url = intent.getStringExtra("url")

        Log.e("TAG", "initView: " + url)
        Glide.with(this)
            .load(url)
            .placeholder(
                R.drawable.ic_image
            ).into(displayBinding.imgView)

    }
}