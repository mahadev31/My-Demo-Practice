package com.demo.resumesqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.demo.resumeusingcomponents.modelclass.ModelClass

class Database(var context: Context) : SQLiteOpenHelper(context, "database", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var table =
            "create table databaseTb(id integer primary key autoincrement,firstName text,lastName text,mobileNumber text,address text,dd text,mm text,yy text,gender text,email text,password text,skills text)"
        db?.execSQL(table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(
        firstName: String?,
        lastName: String?,
        mobileNumber: String?,
        address: String?,
        dd: String?,
        mm: String?,
        yy: String?,
        gender: String?,
        email: String?,
        password: String?,
        skills: String?
    ) {
        var db = writableDatabase
        var c = ContentValues()

        c.put("firstName", firstName)
        c.put("lastName", lastName)
        c.put("mobileNumber", mobileNumber)
        c.put("address", address)
        c.put("dd", dd)
        c.put("mm", mm)
        c.put("yy", yy)
        c.put("gender", gender)
        c.put("email", email)
        c.put("password", password)
        c.put("skills", skills)

        db.insert("databaseTb", null, c)
    }

    fun checkUserData(emailCheck: String, passwordCheck: String): Boolean {
        val db = writableDatabase
        var query =
            "Select * from databaseTb  where email='$emailCheck' and password='$passwordCheck'"
        var cursor = db.rawQuery(query, null)
        if (cursor.count <= 0) {

            cursor.close()
            return false
        }
        cursor.close()
        return true
    }

    fun displayData(): ArrayList<ModelClass> {
        var list = ArrayList<ModelClass>()
        list.clear()

        var db = readableDatabase
        var data = "select  * from databaseTb"
        var c = db.rawQuery(data, null)
        if (c.count > 0) {
            c.moveToFirst()
            do {
                var id = c.getInt(0)
                var firstName = c.getString(1)
                var lastName = c.getString(2)
                var mobilNumber = c.getString(3)
                var address = c.getString(4)
                var dd = c.getString(5)
                var mm = c.getString(6)
                var yy = c.getString(7)
                var gender = c.getString(8)
                var email = c.getString(9)
                var password = c.getString(10)
                var skills = c.getString(11)

                var model = ModelClass(
                    id,
                    firstName,
                    lastName,
                    mobilNumber,
                    address,
                    dd,
                    mm,
                    yy,
                    gender,
                    email,
                    password,
                    skills
                )

                list.add(model)
            } while (c.moveToNext())
        } else {
            Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show()
        }

        return list
    }

    //        fun updateResumeData(
//        id,firstName ,lastName ,mobileNumber ,address ,dd ,mm ,yy ,gender ,email , password , skills
//    ) {
//        var update=writableDatabase
//        var updateSql="update databaseTb set firstName='$firstName',lastName='$lastName',mobileNumber='$mobileNumber',address='$address',dd='$dd',mm='$mm',yy='$yy',gender='$gender',skills='$skills'  where userId='$id'"
//        update.execSQL(updateSql)
//
//        Log.e("updateResumeData", "id: "+id )
//    }
    fun deleteData(id: Int) {
        var db = writableDatabase
        var delete = "delete from databaseTb where id='$id' "
        db.execSQL(delete)
    }
}