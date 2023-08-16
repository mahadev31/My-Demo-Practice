package com.demo.sqlitedatabase.java_pro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.demo.sqlitedatabase.databinding.ActivityShowAllDataBinding;

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



    }
}