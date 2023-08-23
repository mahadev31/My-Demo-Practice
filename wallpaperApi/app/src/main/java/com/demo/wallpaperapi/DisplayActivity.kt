package com.demo.wallpaperapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.demo.wallpaperapi.databinding.ActivityDisplayBinding

class DisplayActivity : AppCompatActivity() {
    lateinit var displayBinding: ActivityDisplayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        displayBinding=ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(displayBinding.root)

        initView()
    }

    private fun initView() {
        var url=intent.getStringExtra("url")

        Glide.with(this)
            .load("https://api.slingacademy.com/public/sample-photos/$url")
            .placeholder(
                R.drawable.ic_image
            ).into(displayBinding.imgView)

    }
}