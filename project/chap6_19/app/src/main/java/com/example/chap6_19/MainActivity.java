package com.example.chap6_19;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chap6_19_activity_main);

        Button showBtn = (Button) findViewById(R.id.showBtn);
        Button hideBtn = (Button) findViewById(R.id.hideBtn);
        Button redBtn = (Button) findViewById(R.id.redBtn);
        Button blackBtn = (Button) findViewById(R.id.blackBtn);
        Button subBtn = (Button) findViewById(R.id.subBtn);

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportActionBar().setTitle("Chap6_19");
                getSupportActionBar().show();
            }
        });
        hideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportActionBar().hide();
            }
        });
        redBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportActionBar().setTitle("빨간 배경");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff0000")));
            }
        });
        blackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportActionBar().setTitle("검은 배경");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
            }
        });
        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportActionBar().setSubtitle("서브 타이틀");
            }
        });
    }
}