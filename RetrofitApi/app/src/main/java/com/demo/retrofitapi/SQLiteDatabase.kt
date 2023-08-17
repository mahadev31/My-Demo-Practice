package com.demo.retrofitapi

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SQLiteDatabase(context: Context) : SQLiteOpenHelper(context, "Database", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var table =
            "create table databaseTb(id text,title text,price text,rating text,description text,thumbnail text,discountPercentage text)"
        db?.execSQL(table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertDatabase(
        id: Int?,
        title: String?,
        price: Int?,
        rating: String?,
        description: String?,
        thumbnail: String?,
        discountPercentage: String?
    ) {

        val db = writableDatabase
        val c = ContentValues()

        c.put("id", id)
        c.put("title", title)
        c.put("price", price)
        c.put("rating", rating)
        c.put("description", description)
        c.put("thumbnail", thumbnail)
        c.put("discountPercentage", discountPercentage)


        Log.e(
            "TAG",
            "insertDatabase: $id  $title  $price  $rating  $description $thumbnail"
        )
        db.insert("databaseTb", null, c)


    }


    fun displayData(): ArrayList<ProductsItem> {
        var list = ArrayList<ProductsItem>()
        list.clear()

        var db = readableDatabase
        var sql = "select * from databaseTb"
        var cursor = db.rawQuery(sql, null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                var id = cursor.getInt(0)
                var title = cursor.getString(1)
                var price = cursor.getInt(2)
                var rating = cursor.getString(3)
                var description = cursor.getString(4)
                var thumbnail = cursor.getString(5)
                var discountPercentage = cursor.getString(6)


                var model = ProductsItem(
                    id = id,
                    title = title,
                    price = price,
                    rating = rating,
                    description = description,
                    thumbnail = thumbnail,
                    discountPercentage = discountPercentage
                )


                Log.e("displayData", "id: $id")
                Log.e("displayData", "title: $title")
                Log.e("displayData", "price: $price")
                Log.e("displayData", "rating: $rating")
                Log.e("displayData", "description: $description")
                Log.e("displayData", "thumbnail: $thumbnail")

                list.add(model)
            } while (cursor.moveToNext())
        }
        return list
    }


}


