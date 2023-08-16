package com.demo.sqlitedatabase.kotlin_pro.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.sqlitedatabase.databinding.ActivityDisplayBinding
import com.demo.sqlitedatabase.kotlin_pro.SQLiteDatabase

class DisplayActivity : AppCompatActivity() {
    lateinit var displayBinding: ActivityDisplayBinding
    lateinit var db: SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        displayBinding = ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(displayBinding.root)

        db = SQLiteDatabase(this)
        initView()
    }

    private fun initView() {
        displayBinding.btnBack.setOnClickListener {
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
        var name = intent.getStringExtra("name")
        var number = intent.getStringExtra("number")

        displayBinding.txtName.text = name
        displayBinding.txtNumber.text = number

        db.insertData(name, number)
    }
}