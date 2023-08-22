package com.demo.allimagefromgallerykotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.allimagefromgallerykotlin.databinding.ActivityMainBinding

class PhotosViewActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var int_position = 0
    var adapter: GridViewAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        int_position = intent.getIntExtra("value", 0)
        adapter = GridViewAdapter(this, MainActivity.imageList, int_position)
        binding.gvFolder.setAdapter(adapter)
    }
}