package com.example.ch5_6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout blue, yellow, black;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("연습문제 5-6");
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ((LinearLayout) findViewById(R.id.blue)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "폭과 높이는 250입니다.", Toast.LENGTH_SHORT).show();
            }
        });
        ((LinearLayout) findViewById(R.id.yellow)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "폭과 높이는 150입니다.", Toast.LENGTH_SHORT).show();
            }
        });
        ((LinearLayout) findViewById(R.id.black)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "폭과 높이는 50입니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}