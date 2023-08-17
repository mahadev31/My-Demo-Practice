package com.demo.resumesqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.resumesqlite.databinding.ActivityFirstPageBinding

class FirstPageActivity : AppCompatActivity() {

    lateinit var firstPageBinding: ActivityFirstPageBinding
    companion object {
        var firstName: String? = null
        var lastName: String? = null
        var mobileNumber: String? = null
        var address: String? = null
        var dd: String? = null
        var mm: String? = null
        var yy: String? = null
        var gender: String? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firstPageBinding= ActivityFirstPageBinding.inflate(layoutInflater)
        setContentView(firstPageBinding.root)

        initView()
    }

    private fun initView() {

    }
}