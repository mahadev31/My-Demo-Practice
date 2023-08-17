package com.demo.sqlitedatabase.java_pro.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.demo.sqlitedatabase.databinding.ActivityInsertDataBinding;
import com.demo.sqlitedatabase.java_pro.Database;

public class InsertDataActivity extends AppCompatActivity {

    ActivityInsertDataBinding insertBinding;

    int id = 0;
    int flag = 0;

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        insertBinding = ActivityInsertDataBinding.inflate(getLayoutInflater());
        setContentView(insertBinding.getRoot());
        db = new Database(this);
        initView();
    }

    private void initView() {
        if (getIntent() != null && getIntent().hasExtra("updateRecord")) {
            flag = 1;
            id = getIntent().getIntExtra("id", 0);
            String itemName = getIntent().getStringExtra("itemName");
            String price = getIntent().getStringExtra("price");

            insertBinding.edtItemName.setText(itemName);
            insertBinding.edtPrice.setText(price);
            Log.e("TAG", "initView: " + id);
        }
        insertBinding.btnSave.setOnClickListener(v -> {
            String itemName = insertBinding.edtItemName.getText().toString();
            String price = insertBinding.edtPrice.getText().toString();

            if (itemName.isEmpty()) {
                Toast.makeText(this, "itemName is Empty", Toast.LENGTH_SHORT).show();
            } else if (price.isEmpty()) {
                Toast.makeText(this, "price is Empty", Toast.LENGTH_SHORT).show();
            } else {
                if (flag == 1) {
                    db.updateData(id, itemName, price);
                    Intent i =new Intent(this, ShowAllDataActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(this, ShowDataActivity.class);
                    i.putExtra("itemName", itemName);
                    i.putExtra("price", price);
                    startActivity(i);
                }
            }
        });
    }
}