package com.demo.sqlitedatabase.java_pro.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.demo.sqlitedatabase.databinding.ActivityShowAllDataBinding;
import com.demo.sqlitedatabase.java_pro.Adapter;
import com.demo.sqlitedatabase.java_pro.Database;
import com.demo.sqlitedatabase.java_pro.Model;
import com.demo.sqlitedatabase.java_pro.TotalAll_Interface;

import java.util.ArrayList;

public class ShowAllDataActivity extends AppCompatActivity {

    ActivityShowAllDataBinding allDataBinding;
    ArrayList<Model> list = new ArrayList<>();
    Database db;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allDataBinding = ActivityShowAllDataBinding.inflate(getLayoutInflater());
        setContentView(allDataBinding.getRoot());
        db = new Database(this);
        initView();
    }

    private void initView() {

        TotalAll_Interface totalAll = new TotalAll_Interface() {
            @Override
            public void totalAll() {
                amount();

            }
        };
        list = db.displayData();
        adapter = new Adapter(this,totalAll);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        allDataBinding.rcvView.setLayoutManager(manager);
        allDataBinding.rcvView.setAdapter(adapter);

        adapter.updateList(list);


    }

    private void amount() {

        int totalAmount = adapter.totalFunction();

        Log.e("tra", "totalAmount:" + totalAmount);


        allDataBinding.txtTotal.setText(String.valueOf(totalAmount));


    }
}