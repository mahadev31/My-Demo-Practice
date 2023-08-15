package com.demo.sqlitedatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.demo.sqlitedatabase.databinding.ActivityAddDataBinding

class AddDataActivity : AppCompatActivity() {
    lateinit var addDataBinding: ActivityAddDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addDataBinding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(addDataBinding.root)
        var id=0
        var flag=0
        initView()
    }

    private fun initView() {
        if (intent != null && intent.hasExtra("updateRecord")){
            var id=intent.getIntExtra("id",0)
            var name=intent.getStringExtra("name")
            var number=intent.getStringExtra("number")

            addDataBinding.edtName.text=name
          addDataBinding.edtNumber.text=number
        }
        addDataBinding.btnSave.setOnClickListener {
            var name = addDataBinding.edtName.text.toString()
            var number = addDataBinding.edtNumber.text.toString()

            if (name.isEmpty()) {
                Toast.makeText(this, "Name is Empty", Toast.LENGTH_SHORT).show()
            } else if (number.isEmpty()) {
                Toast.makeText(this, "Number is Empty", Toast.LENGTH_SHORT).show()
            } else {
                var i = Intent(this, DisplayActivity::class.java)
                i.putExtra("name", name)
                i.putExtra("number", number)
                startActivity(i)

            }
        }
    }
}