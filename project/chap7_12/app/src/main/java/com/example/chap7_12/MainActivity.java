package com.example.chap7_12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("토스트 연습");
        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);

                int x = (int) (Math.random()*metrics.widthPixels);
                int y = (int) (Math.random()*metrics.heightPixels);

                Toast msg = Toast.makeText(getApplicationContext(), "토스트 연습", Toast.LENGTH_SHORT);
                msg.setGravity(Gravity.TOP | Gravity.LEFT, x, y);
                msg.show();
            }
        });
    }
}