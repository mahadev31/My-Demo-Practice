package com.demo.sqlitedatabase.java_pro.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.demo.sqlitedatabase.databinding.ActivityDashbordBinding;

public class DashboardActivity extends AppCompatActivity {

    ActivityDashbordBinding  binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDashbordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {

        binding.btnEnter.setOnClickListener(v->{
            Intent i=new Intent(this,InsertDataActivity.class);
            startActivity(i);
        });
        binding.btnShow.setOnClickListener(v->{
            Intent i=new Intent(this,ShowAllDataActivity.class);
            startActivity(i);
        });
    }
}