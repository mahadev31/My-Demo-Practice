package com.demo.sqlitedatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.sqlitedatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        initView();
    }

    private fun initView() {
        mainBinding.btnEnter.setOnClickListener {
            var i = Intent(this, AddDataActivity::class.java)
            startActivity(i)
        }
        mainBinding.btnShow.setOnClickListener {
            var i = Intent(this, AllDataActivity::class.java)
            startActivity(i)
        }
    }
}