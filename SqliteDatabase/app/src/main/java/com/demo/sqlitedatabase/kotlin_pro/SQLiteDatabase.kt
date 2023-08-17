package com.demo.sqlitedatabase.kotlin_pro

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.demo.sqlitedatabase.kotlin_pro.model.ModelClass

class SQLiteDatabase(var context: Context) : SQLiteOpenHelper(context, "database", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var table =
            "create table databaseTb(id integer primary key autoincrement,itemName text, price text)"
        db?.execSQL(table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(itemName: String?, price: String?) {

        var db = writableDatabase
        var c = ContentValues()
        c.put("itemName", itemName)
        c.put("price", price)
        db.insert("databaseTb", null, c)

        Log.e("TAG", "insertData: $itemName  $price")
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
                var itemName = cursor.getString(1)
                var price = cursor.getString(2)

                var model = ModelClass(id, itemName, price)
                list.add(model)
            } while (cursor.moveToNext())
        } else {
            Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show()
        }

        return list
    }

    fun updateData(id: Int, itemName: String, price: String) {

        var db = writableDatabase
        var update = "update databaseTb set itemName='$itemName',price='$price' where id='$id'"
        db.execSQL(update)
    }

    fun deleteData(id: Int) {
        var db = writableDatabase
        var delete = "delete from databaseTb where id='$id'"
        db.execSQL(delete)
    }
}