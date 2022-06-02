package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        setTitle("상세정보");

        ImageView img = findViewById(R.id.img);
        TextView name = findViewById(R.id.name);
        TextView phone = findViewById(R.id.phone);

        // 여기에 소스 작성....





    }
}