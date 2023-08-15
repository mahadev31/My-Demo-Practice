package com.demo.sqlitedatabase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

class SQLiteDatabase(var context: Context) : SQLiteOpenHelper(context, "database", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var table =
            "create table databaseTb(id integer primary key autoincrement,name text, number text)"
        db?.execSQL(table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(name: String?, number: String?) {

        var db = writableDatabase
        var c = ContentValues()
        c.put("name", name)
        c.put("number", number)
        db.insert("databaseTb", null, c)

        Log.e("TAG", "insertData: $name  $number")
    }

    fun displayData(): ArrayList<ModelClass> {
        var list = ArrayList<ModelClass>()
        list.clear()
        var db = readableDatabase
        var sql = "select * from databaseTb"
        var cursor = db.rawQuery(sql, null)
        if (cursor.count > 0) {

            cursor.moveToFirst()
            do {
                var id = cursor.getInt(0)
                var name = cursor.getString(1)
                var number = cursor.getString(2)

                var model = ModelClass(id, name, number)
                list.add(model)
            } while (cursor.moveToNext())
        } else {
            Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show()
        }

        return list
    }
}