package com.demo.sqlitedatabase.java_pro.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.demo.sqlitedatabase.databinding.ActivityShowAllDataBinding;
import com.demo.sqlitedatabase.java_pro.Adapter;
import com.demo.sqlitedatabase.java_pro.Database;
import com.demo.sqlitedatabase.java_pro.Model;

import java.util.ArrayList;

public class ShowAllDataActivity extends AppCompatActivity {

    ActivityShowAllDataBinding allDataBinding;
    ArrayList<Model> list = new ArrayList<>();
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allDataBinding = ActivityShowAllDataBinding.inflate(getLayoutInflater());
        setContentView(allDataBinding.getRoot());
        db = new Database(this);
        initView();
    }

    private void initView() {

        list=db.displayData();
        Adapter adapter=new Adapter(this);
        LinearLayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        allDataBinding.rcvView.setLayoutManager(manager);
        allDataBinding.rcvView.setAdapter(adapter);

        adapter.updateList(list);


    }
}