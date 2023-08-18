package com.demo.resumesqlite.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.demo.resumesqlite.Database
import com.demo.resumesqlite.databinding.ActivityLoginPageBinding

class LoginPageActivity : AppCompatActivity() {

    lateinit var loginPageBinding: ActivityLoginPageBinding

    lateinit var db: Database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginPageBinding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(loginPageBinding.root)

        db = Database(this)
        initView()
    }

    private fun initView() {
        loginPageBinding.btnSignIn.setOnClickListener {

            var emailCheck = loginPageBinding.edtEmail.text.toString()
            var passwordCheck = loginPageBinding.edtPassword.text.toString()

            var checkUser = db.checkUserData(emailCheck, passwordCheck)

            if (checkUser == true) {
                val i = Intent(this, MainActivity::class.java)
                i.putExtra("email", emailCheck)
                loginPageBinding.edtEmail.setText("")
                loginPageBinding.edtPassword.setText("")
                startActivity(i)
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(this, "invalid userName and password", Toast.LENGTH_SHORT).show()
            }
        }

        loginPageBinding.linSignUp.setOnClickListener {
            val i = Intent(this, FirstPageActivity::class.java)
            startActivity(i)
        }
    }
}