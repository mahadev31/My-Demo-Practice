package com.demo.retrofitapi

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.retrofitapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding

    lateinit var apiInterfaceM: APIInterface

    lateinit var sharedPreferences: SharedPreferences

    lateinit var db: SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        sharedPreferences = getSharedPreferences("mySharePref", MODE_PRIVATE)
        db = SQLiteDatabase(this)
        initView()
    }

    private fun initView() {

        if (sharedPreferences.getBoolean("firstTime", false) == true) {

           var list = db.displayData()

            var adapterClass = ProductsAdapterClass(this@MainActivity, list) {}
            var manager = GridLayoutManager(this@MainActivity, 2)
            mainBinding.rcvView.layoutManager = manager
            mainBinding.rcvView.adapter = adapterClass

            Toast.makeText(this, "Sqlite Database Show", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "Api Call", Toast.LENGTH_SHORT).show()
            apiInterfaceM = APIClient.getClient()!!.create(APIInterface::class.java)
            apiInterfaceM.getData().enqueue(object : Callback<Products> {
                override fun onResponse(call: Call<Products>, response: Response<Products>) {

                    var myEdit: SharedPreferences.Editor = sharedPreferences.edit()
                    myEdit.putBoolean("firstTime", true)
                    myEdit.commit()

                    var productList = response.body()?.products

                    var adapterClass = ProductsAdapterClass(this@MainActivity, productList) {}
                    val manager = GridLayoutManager(this@MainActivity, 2)
                    mainBinding.rcvView.layoutManager = manager
                    mainBinding.rcvView.adapter = adapterClass

                    for (i in 0 until productList!!.size) {
                        var id = productList[i].id
                        var title = productList[i].title
                        var price = productList[i].price
                        var rating = productList[i].rating
                        var description = productList[i].description
                        var thumbnail = productList[i].thumbnail
                        var discountPercentage = productList[i].discountPercentage

                        db.insertDatabase(id, title, price, rating, description, thumbnail,discountPercentage)

                    }
                }

                override fun onFailure(call: Call<Products>, t: Throwable) {
                    Log.e("TAG", "onFailure: "+t.message )
                }
            })
        }
    }
}