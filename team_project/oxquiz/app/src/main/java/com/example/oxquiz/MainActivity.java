package com.example.oxquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView today, content;
    Button refresh, btn1, btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        loadToday();
    }

    void init() {
        today = findViewById(R.id.today);
        content = findViewById(R.id.content);
        refresh = findViewById(R.id.refresh);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
    }

    void loadToday() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        today.setText(dateFormat.format(date));
    }

    void setQuizContent(String str) {
        content.setText(str);
    }

    void setBtnText(int idx, String str) {
        switch (idx) {
            case 1:
                btn1.setText("① " + str);
                return;
            case 2:
                btn1.setText("② " + str);
                return;
            case 3:
                btn1.setText("③ " + str);
                return;
            case 4:
                btn1.setText("④ " + str);
                return;
            default:
        }
    }
}