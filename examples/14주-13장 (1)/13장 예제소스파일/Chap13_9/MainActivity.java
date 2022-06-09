package com.example.chap13_9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar pb1, pb2;
        final Button btn;
        pb1 = (ProgressBar) findViewById(R.id.pb1);
        pb2 = (ProgressBar) findViewById(R.id.pb2);
        btn = (Button) findViewById(R.id.button1);

//        new Thread(){
//            @Override
//            public void run() {
//
//            }
//        }.start();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){ //for pb1
                    @Override
                    public void run() {
                        for (int i = pb1.getProgress(); i < 100; i = i + 2) {
                            pb1.setProgress(pb1.getProgress() + 2);
                            SystemClock.sleep(100);
                        }
                    }
                }.start();
                new Thread(){ //for pb2
                    @Override
                    public void run() {
                        for (int i = pb2.getProgress(); i < 100; i++) {
                            pb2.setProgress(pb2.getProgress() + 1);
                            SystemClock.sleep(100);
                        }
                    }
                }.start();
            }
        });

//        btn.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                for (int i = 0; i < 100; i++) {
//                    pb1.setProgress(pb1.getProgress() + 2);
//                    pb2.setProgress(pb1.getProgress() + 1);
//                    SystemClock.sleep(30);
//                }
//            }
//        });
    }
}