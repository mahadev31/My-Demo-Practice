package com.demo.resumesqlite.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.resumesqlite.Database
import com.demo.resumesqlite.databinding.ActivityMainBinding
import com.demo.resumeusingcomponents.adapter.MainAdapterClass
import com.demo.resumeusingcomponents.modelclass.ModelClass

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding

    lateinit var db: Database
    var list = ArrayList<ModelClass>()
    lateinit var adapter: MainAdapterClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        db = Database(this)
        initView()
    }

    private fun initView() {

        var email = intent.getStringExtra("email")

        mainBinding.txtEmail.text = email
        adapter = MainAdapterClass(list, {
//            var i = Intent(this, DisplayResumeActivity::class.java)
//            i.putExtra("fName", it.firstName)
//            i.putExtra("lName", it.lastName)
//            i.putExtra("mNumber", it.mobileNumber)
//            i.putExtra("address", it.address)
//            i.putExtra("email", it.emailId)
//            i.putExtra("gender", it.gender)
//            i.putExtra("dd", it.dd)
//            i.putExtra("mm", it.mm)
//            i.putExtra("yy", it.yy)
//            i.putExtra("skill", it.skill)
//            startActivity(i)
        }, {
//
//            var editIntent = Intent(this, FirstPageActivity::class.java)
//            editIntent.putExtra("id", it.id)
//            editIntent.putExtra("firstName", it.firstName)
//            editIntent.putExtra("lastName", it.lastName)
//            editIntent.putExtra("mobileNumber", it.mobileNumber)
//            editIntent.putExtra("address", it.address)
//            editIntent.putExtra("hobby1", it.dd)
//            editIntent.putExtra("hobby2", it.mm)
//            editIntent.putExtra("hobby3", it.yy)
//            editIntent.putExtra("gender", it.gender)
//            editIntent.putExtra("email", it.emailId)
//            editIntent.putExtra("password", it.password)
//            editIntent.putExtra("skills", it.skill)
//            editIntent.putExtra("updateRecord", true)
//            startActivity(editIntent)

//            Log.e("displayUpdate", "id: " + it.id)
//            Log.e("displayUpdate", "fName: " + it.firstName)
        }, { id ->
            db.deleteData(id)
            Toast.makeText(this, "delete record success", Toast.LENGTH_SHORT).show()
            list = db.displayData()
            adapter.updateList(list)

        })
        var manger = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mainBinding.rcvView.layoutManager = manger
        mainBinding.rcvView.adapter = adapter

        list = db.displayData()
        adapter.updateList(list)
    }
}