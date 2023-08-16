package com.demo.retrofitapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.retrofitapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding

    lateinit var apiInterfaceM:APIInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        initView()
    }

    private fun initView() {

        apiInterfaceM = APIClient.getClient()!!.create(APIInterface::class.java)
        apiInterfaceM.getData().enqueue(object : Callback<Products> {
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                var productList = response.body()?.products
                var adapterClass = ProductsAdapterClass(this@MainActivity, productList)
                {

                }
                val manager = GridLayoutManager(this@MainActivity, 2)
                mainBinding.rcvView.layoutManager = manager
                mainBinding.rcvView.adapter = adapterClass
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}