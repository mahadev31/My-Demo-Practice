package com.demo.allimagefromgallerykotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.allimagefromgallerykotlin.databinding.ActivityMainBinding

class PhotosViewActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var int_position = 0
    lateinit var adapter: GridViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        int_position = intent.getIntExtra("value", 0)
        adapter = GridViewAdapter(this, MainActivity.al_images, int_position)
        binding.gvFolder.adapter = adapter
    }
}