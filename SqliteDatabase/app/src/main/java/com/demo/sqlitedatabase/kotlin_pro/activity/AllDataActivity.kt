package com.demo.sqlitedatabase.kotlin_pro.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.sqlitedatabase.kotlin_pro.adapter.AdapterClass
import com.demo.sqlitedatabase.kotlin_pro.model.ModelClass
import com.demo.sqlitedatabase.databinding.ActivityAllDataBinding
import com.demo.sqlitedatabase.kotlin_pro.SQLiteDatabase

class AllDataActivity : AppCompatActivity() {
    lateinit var allDataBinding: ActivityAllDataBinding
    lateinit var db: SQLiteDatabase
    var list = ArrayList<ModelClass>()
    lateinit var adapter: AdapterClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        allDataBinding = ActivityAllDataBinding.inflate(layoutInflater)
        setContentView(allDataBinding.root)

        db = SQLiteDatabase(this)

        initView()
    }

    private fun initView() {
        list = db.displayData()
        adapter = AdapterClass(this) {
            amount()
        }
        var manger = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        allDataBinding.rcvView.layoutManager = manger
        allDataBinding.rcvView.adapter = adapter

        adapter.updateList(list)


    }

    private fun amount() {

        var totalAmount = adapter.totalFunction()

        Log.e("tra", "totalAmount: $totalAmount")


        allDataBinding.txtTotal.text = totalAmount.toString()



    }
}