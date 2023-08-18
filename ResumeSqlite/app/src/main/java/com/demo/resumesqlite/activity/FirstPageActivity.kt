package com.demo.resumesqlite.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.RadioButton
import android.widget.Toast
import com.demo.resumesqlite.databinding.ActivityFirstPageBinding

class FirstPageActivity : AppCompatActivity() {

    lateinit var firstPageBinding: ActivityFirstPageBinding

    companion object {
        var firstName: String? = null
        var lastName: String? = null
        var mobileNumber: String? = null
        var address: String? = null
        var dd: String? = null
        var mm: String? = null
        var yy: String? = null
        var gender: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firstPageBinding = ActivityFirstPageBinding.inflate(layoutInflater)
        setContentView(firstPageBinding.root)

        initView()
    }

    private fun initView() {

        firstPageBinding.btnNext.setOnClickListener {
            firstName = firstPageBinding.edtFirstName.text.toString()
            lastName = firstPageBinding.edtLastName.text.toString()
            mobileNumber = firstPageBinding.edtMobilNumber.text.toString()
            address = firstPageBinding.edtAddress.text.toString()
            dd = firstPageBinding.edtDD.text.toString()
            mm = firstPageBinding.edtMM.text.toString()
            yy = firstPageBinding.edtYY.text.toString()

            //Gender
            val selectedId: Int = firstPageBinding.rgGender.checkedRadioButtonId
            val rb = findViewById<RadioButton>(selectedId)

            if (selectedId != -1) {
                gender = rb.text as String?
            }


            if (firstName!!.isEmpty()) {
                Toast.makeText(this, "First Name is Empty", Toast.LENGTH_SHORT).show()
            } else if (lastName!!.isEmpty()) {
                Toast.makeText(this, "Last Name is Empty", Toast.LENGTH_SHORT).show()
            } else if (mobileNumber!!.isEmpty()) {
                Toast.makeText(this, "Mobile Number is Empty", Toast.LENGTH_SHORT).show()
            } else if (mobileNumber!!.length <= 10 != mobileNumber!!.length >= 10) {
                Toast.makeText(this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show()

            } else if (!Patterns.PHONE.matcher(mobileNumber).matches()) {
                Toast.makeText(this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show()

            } else if (address!!.isEmpty()) {
                Toast.makeText(this, "Address is Empty", Toast.LENGTH_SHORT).show()
            } else if (dd!!.isEmpty()) {
                Toast.makeText(this, "Date of Birth  is Empty", Toast.LENGTH_SHORT).show()
            } else if (mm!!.isEmpty()) {
                Toast.makeText(this, "Date of Birth  is Empty", Toast.LENGTH_SHORT).show()
            } else if (yy!!.isEmpty()) {
                Toast.makeText(this, "Date of Birth  is Empty", Toast.LENGTH_SHORT).show()
            } else if (dd!!.length < 2 != mm!!.length < 2 != yy!!.length < 4) {
                Toast.makeText(this, "Please Enter Date of Birth  Valid", Toast.LENGTH_SHORT).show()
            } else if (selectedId == -1) {
                Toast.makeText(this, "Gender is not Selected", Toast.LENGTH_SHORT).show()
            } else {

                var i = Intent(this, SecondPageActivity::class.java)
                startActivity(i)
            }
        }
    }
}