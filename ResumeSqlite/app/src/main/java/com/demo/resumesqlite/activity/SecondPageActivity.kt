package com.demo.resumesqlite.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.demo.resumesqlite.databinding.ActivitySecondPageBinding

class SecondPageActivity : AppCompatActivity() {
    lateinit var secondPageBinding: ActivitySecondPageBinding

    companion object {
        var email: String? = null
        var password: String? = null
        var confirm_password: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondPageBinding = ActivitySecondPageBinding.inflate(layoutInflater)
        setContentView(secondPageBinding.root)

        initView()
    }

    private fun initView() {
        secondPageBinding.btnNext.setOnClickListener {
            email = secondPageBinding.edtEmail.text.toString()
            password = secondPageBinding.edtPassword.text.toString()
            confirm_password = secondPageBinding.edtConfirmPassword.text.toString()

            if (email!!.isEmpty()) {
                Toast.makeText(this, "Email  is Empty", Toast.LENGTH_SHORT).show()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show()

            } else if (password!!.isEmpty()) {
                Toast.makeText(this, "Password is Empty", Toast.LENGTH_SHORT).show()
            } else if (password!!.length < 8) {
                Toast.makeText(this, "Minimum 8 Character Password", Toast.LENGTH_SHORT).show()

            } else if (!password!!.matches(".*[A-Z].*".toRegex())) {

                Toast.makeText(this, "Must Contain 1 Upper-case Character", Toast.LENGTH_SHORT)
                    .show()

            } else if (!password!!.matches(".*[a-z].*".toRegex())) {
                Toast.makeText(this, "Must Contain 1 Lower-case Character", Toast.LENGTH_SHORT)
                    .show()

            } else if (!password!!.matches(".*[@#\$%^&+=].*".toRegex())) {
                Toast.makeText(
                    this,
                    "Must Contain 1 Special Character (@#\$%^&+=)",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (confirm_password!!.isEmpty()) {
                Toast.makeText(this, "Confirm Password is Empty", Toast.LENGTH_SHORT).show()
            } else if (confirm_password!! != password) {
                Toast.makeText(this, "Confirm Password Do not Match", Toast.LENGTH_SHORT).show()
            } else {
                var i = Intent(this, ThirdPageActivity::class.java)
                startActivity(i)
            }
        }
    }
}