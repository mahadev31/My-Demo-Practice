package com.demo.sqlitedatabase.kotlin_pro.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.demo.sqlitedatabase.databinding.ActivityAddDataBinding
import com.demo.sqlitedatabase.kotlin_pro.SQLiteDatabase

class AddDataActivity : AppCompatActivity() {
    lateinit var addDataBinding: ActivityAddDataBinding
    lateinit var db: SQLiteDatabase

    var id = 0
    var flag = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addDataBinding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(addDataBinding.root)

        db = SQLiteDatabase(this)
        initView()
    }

    private fun initView() {
        if (intent != null && intent.hasExtra("updateRecord")) {
            flag = 1
             id = intent.getIntExtra("id", 0)
            var itemName = intent.getStringExtra("itemName")
            var price = intent.getStringExtra("price")

            addDataBinding.edtItemName.setText(itemName)
            addDataBinding.edtPrice.setText(price)
            Log.e("TAG", "initView: " + id)
        }
        addDataBinding.btnSave.setOnClickListener {
            var itemName = addDataBinding.edtItemName.text.toString()
            var price = addDataBinding.edtPrice.text.toString()

            if (itemName.isEmpty()) {
                Toast.makeText(this, "item Name is Empty", Toast.LENGTH_SHORT).show()
            } else if (price.isEmpty()) {
                Toast.makeText(this, "price is Empty", Toast.LENGTH_SHORT).show()
            } else {

                if (flag == 1) {
                    db.updateData(id, itemName, price)
                    var i = Intent(this, AllDataActivity::class.java)
                    startActivity(i)
                } else {
                    var i = Intent(this, DisplayActivity::class.java)
                    i.putExtra("itemName", itemName)
                    i.putExtra("price", price)
                    startActivity(i)
                }
            }
        }
    }
}