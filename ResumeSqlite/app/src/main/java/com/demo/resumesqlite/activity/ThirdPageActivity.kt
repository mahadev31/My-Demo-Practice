package com.demo.resumesqlite.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.demo.resumesqlite.databinding.ActivityThirdPageBinding

class ThirdPageActivity : AppCompatActivity() {

    lateinit var thirdPageBinding: ActivityThirdPageBinding

    companion object {
        var skills: String? = null

    }

    var skill = StringBuilder()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        thirdPageBinding = ActivityThirdPageBinding.inflate(layoutInflater)
        setContentView(thirdPageBinding.root)

        initView()
    }

    private fun initView() {
        thirdPageBinding.btnSubmit.setOnClickListener {
            if (thirdPageBinding.chkC.isChecked) {
                skill.append(thirdPageBinding.chkC.text.toString() + ",")
            }
            if (thirdPageBinding.chkCPlush.isChecked) {
                skill.append(thirdPageBinding.chkCPlush.text.toString() + ",")
            }
            if (thirdPageBinding.chkJava.isChecked) {
                skill.append(thirdPageBinding.chkJava.text.toString() + ",")
            }
            if (thirdPageBinding.chkKotlin.isChecked) {
                skill.append(thirdPageBinding.chkKotlin.text.toString() + ",")
            }
            if (thirdPageBinding.chkAndroid.isChecked) {
                skill.append(thirdPageBinding.chkAndroid.text.toString() + ",")
            }
            if (thirdPageBinding.chkSQLite.isChecked) {
                skill.append(thirdPageBinding.chkSQLite.text.toString() + ",")
            }
            if (thirdPageBinding.chkFigma.isChecked) {
                skill.append(thirdPageBinding.chkFigma.text.toString() + ",")
            }
            if (thirdPageBinding.chkWab.isChecked) {
                skill.append(thirdPageBinding.chkWab.text.toString() + ",")
            }
            if (thirdPageBinding.chkFlutter.isChecked) {
                skill.append(thirdPageBinding.chkFlutter.text.toString())
            }
            skills = skill.toString()
            if (!thirdPageBinding.chkC.isChecked && !thirdPageBinding.chkCPlush.isChecked && !thirdPageBinding.chkJava.isChecked && !thirdPageBinding.chkKotlin.isChecked && !thirdPageBinding.chkSQLite.isChecked && !thirdPageBinding.chkAndroid.isChecked && !thirdPageBinding.chkWab.isChecked && !thirdPageBinding.chkFlutter.isChecked && !thirdPageBinding.chkFigma.isChecked) {
                Toast.makeText(this, "Hobby is not selected", Toast.LENGTH_SHORT).show()
            } else {
                var i = Intent(this, DisplayDataActivity::class.java)
                startActivity(i)

            }
        }
    }
}