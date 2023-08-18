package com.demo.resumesqlite.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.resumesqlite.Database
import com.demo.resumesqlite.databinding.ActivityDisplayDataBinding

class DisplayDataActivity : AppCompatActivity() {

    lateinit var displayDataBinding: ActivityDisplayDataBinding
    lateinit var db: Database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        displayDataBinding = ActivityDisplayDataBinding.inflate(layoutInflater)
        setContentView(displayDataBinding.root)

        db = Database(this)
        initView()
    }

    private fun initView() {
        displayDataBinding.txtFirstName.text = FirstPageActivity.firstName
        displayDataBinding.txtLastName.text = FirstPageActivity.lastName
        displayDataBinding.txtMobileNumber.text = FirstPageActivity.mobileNumber
        displayDataBinding.txtAddress.text = FirstPageActivity.address
        displayDataBinding.txtDD.text = FirstPageActivity.dd
        displayDataBinding.txtMM.text = FirstPageActivity.mm
        displayDataBinding.txtYY.text = FirstPageActivity.yy
        displayDataBinding.txtGender.text = FirstPageActivity.gender

        displayDataBinding.txtEmail.text = SecondPageActivity.email
        displayDataBinding.txtPassword.text = SecondPageActivity.password

        displayDataBinding.txtSkill.text = ThirdPageActivity.skills


        displayDataBinding.btnAddData.setOnClickListener {

            db.insertData(
                FirstPageActivity.firstName,
                FirstPageActivity.lastName,
                FirstPageActivity.mobileNumber,
                FirstPageActivity.address,
                FirstPageActivity.dd,
                FirstPageActivity.mm,
                FirstPageActivity.yy,
                FirstPageActivity.gender,

                SecondPageActivity.email,
                SecondPageActivity.password,

                ThirdPageActivity.skills,
            )
            var i = Intent(this, LoginPageActivity::class.java)
            startActivity(i)

        }
    }
}