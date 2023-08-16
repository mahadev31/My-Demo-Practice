package com.demo.sqlitedatabase.java_pro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    Context context;

    public Database(Context context) {
        super(context, "database", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String table = "Create table databaseTb(id integer primary key autoincrement,name text,number text)";
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(String name, String number) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues c = new ContentValues();

        c.put("name", name);
        c.put("number", number);

        db.insert("databaseTb", null, c);

        Log.e("TAG", "insertData: " + name + " " + number);
    }

    public ArrayList<Model> displayData() {
        ArrayList<Model> list = new ArrayList<>();
        list.clear();

        SQLiteDatabase db = getReadableDatabase();
        String display = "select * from databaseTb";
        Cursor cursor = db.rawQuery(display, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String number = cursor.getString(2);

                Model model = new Model(id, name, number);
                list.add(model);
                Log.e("TAG", "displayData: " + id + " " + name + " " + number);
            } while (cursor.moveToNext());

        } else {
            Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show();
        }

        return list;
    }


    public void deleteData(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String delete = "delete from databaseTb where id=" + id;
        db.execSQL(delete);
    }


}
