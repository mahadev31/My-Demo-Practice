package com.demo.sqlitedatabase.java_pro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.demo.sqlitedatabase.databinding.ActivityInsertDataBinding;

public class InsertDataActivity extends AppCompatActivity {

    ActivityInsertDataBinding insertBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        insertBinding = ActivityInsertDataBinding.inflate(getLayoutInflater());
        setContentView(insertBinding.getRoot());

        initView();
    }

    private void initView() {

        insertBinding.btnSave.setOnClickListener(v -> {
            String name = insertBinding.edtName.getText().toString();
            String number = insertBinding.edtNumber.getText().toString();

            if (name.isEmpty()) {
                Toast.makeText(this, "Name is Empty", Toast.LENGTH_SHORT).show();
            } else if (number.isEmpty()) {
                Toast.makeText(this, "Number is Empty", Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent(this, ShowDataActivity.class);
                i.putExtra("name", name);
                i.putExtra("number", number);
                startActivity(i);
            }
        });
    }
}