package com.demo.sqlitedatabase.java_pro.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.demo.sqlitedatabase.databinding.ActivityShowDataBinding;
import com.demo.sqlitedatabase.java_pro.Database;

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
        showBinding.btnBack.setOnClickListener(v->{
            Intent i=new Intent(this,DashboardActivity.class);
            startActivity(i);
        });
        String itemName = getIntent().getStringExtra("itemName");
        String price = getIntent().getStringExtra("price");

        showBinding.txtName.setText(itemName);
        showBinding.txtNumber.setText(price);

        db.insertData(itemName,price);

        Toast.makeText(this, "Data Store", Toast.LENGTH_SHORT).show();
    }
}