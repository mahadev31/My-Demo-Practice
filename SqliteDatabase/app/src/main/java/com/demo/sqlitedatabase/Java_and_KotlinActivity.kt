package com.demo.sqlitedatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.sqlitedatabase.databinding.ActivityJavaAndKotlinBinding
import com.demo.sqlitedatabase.java_pro.activity.DashboardActivity
import com.demo.sqlitedatabase.kotlin_pro.activity.MainActivity

class Java_and_KotlinActivity : AppCompatActivity() {
    lateinit var binding: ActivityJavaAndKotlinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityJavaAndKotlinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.btnJava.setOnClickListener {
            var i = Intent(this, DashboardActivity::class.java)
            startActivity(i)
        }
        binding.btnKotlin.setOnClickListener {
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }
}