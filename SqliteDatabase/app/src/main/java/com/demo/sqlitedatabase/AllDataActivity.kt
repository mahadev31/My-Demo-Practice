package com.demo.sqlitedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.sqlitedatabase.databinding.ActivityAllDataBinding

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
        adapter = AdapterClass(this)
        var manger = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        allDataBinding.rcvView.layoutManager = manger
        allDataBinding.rcvView.adapter = adapter

        adapter.updateList(list)


    }
}