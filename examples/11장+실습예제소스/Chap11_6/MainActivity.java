package com.example.chap11_6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("스피너 테스트");

        String[] movie = { "쿵푸팬더", "짱구는 못말려", "아저씨",
                           "아바타", "대부", "국가대표", "토이스토리3",
                           "마당을 나온 암탉", "죽은 시인의 사회", "서유기" };

        Spinner spinner = findViewById(R.id.spinner1);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, movie);
        spinner.setAdapter(adapter);
    }
}