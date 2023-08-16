package com.demo.sqlitedatabase.java_pro;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {
    public Database(Context context) {
        super(context,"database",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String table="Create table databaseTb(id integer primary key autoincrement,name text,number text)";
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(String name, String number) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues c=new ContentValues();

        c.put("name",name);
        c.put("number",number);

        db.insert("databaseTb",null,c);

        Log.e("TAG", "insertData: "+name +" "+number );
    }
}
