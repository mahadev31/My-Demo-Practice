package com.demo.wallpaperapi.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.wallpaperapi.APIInterface
import com.demo.wallpaperapi.adapter.AdapterClass
import com.demo.wallpaperapi.model.Response
import com.demo.wallpaperapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding
    lateinit var apiInterfaceM: APIInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        initView()
    }

    private fun initView() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.slingacademy.com/v1/sample-data/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterfaceM = retrofit.create(APIInterface::class.java)
        val call = apiInterfaceM.getData()
        call.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                var list = response.body()?.photos
                Log.e("TAG", "onResponse: $list")

                var adapterClass = AdapterClass(this@MainActivity, list) {
                    var i = Intent(this@MainActivity, DisplayActivity::class.java)
                    i.putExtra("url", it)
                    startActivity(i)
                }
                val manager = GridLayoutManager(this@MainActivity, 2)
                mainBinding.rcvView.layoutManager = manager
                mainBinding.rcvView.adapter = adapterClass
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}")
            }
        })
    }
}