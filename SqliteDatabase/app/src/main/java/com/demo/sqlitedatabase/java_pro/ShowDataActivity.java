package com.demo.sqlitedatabase.java_pro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.demo.sqlitedatabase.databinding.ActivityShowDataBinding;

public class ShowDataActivity extends AppCompatActivity {

    ActivityShowDataBinding showBinding;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showBinding = ActivityShowDataBinding.inflate(getLayoutInflater());
        setContentView(showBinding.getRoot());
        db = new Database(this);
        initView();
    }

    private void initView() {

        String name = getIntent().getStringExtra("name");
        String number = getIntent().getStringExtra("number");

        showBinding.txtName.setText(name);
        showBinding.txtNumber.setText(number);

        db.insertData(name,number);

    }
}