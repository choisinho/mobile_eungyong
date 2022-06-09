package com.example.chap13_12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar pb1 = (ProgressBar) findViewById(R.id.pb1);
        final ProgressBar pb2 = (ProgressBar) findViewById(R.id.pb2);
        Button btn = (Button) findViewById(R.id.button1);
        final TextView tv1 = (TextView) findViewById(R.id.tv1);
        final TextView tv2 = (TextView) findViewById(R.id.tv2);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread() { //for pb1
                    public void run() {
                        for (int i = pb1.getProgress(); i < 100; i = i + 2) {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    pb1.setProgress(pb1.getProgress() + 2);
                                    tv1.setText("1번 진행률 : "+pb1.getProgress()+"%");
                                }
                            });
                            SystemClock.sleep(100);
                        }
                    }
                }.start();
                new Thread() { //for pb2
                    public void run() {
                        for (int i = pb2.getProgress(); i < 100; i++) {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    pb2.setProgress(pb2.getProgress() + 1);
                                    tv2.setText("2번 진행률 : "+pb2.getProgress()+"%");
                                }
                            });
                            SystemClock.sleep(100);
                        }
                    }
                }.start();
            }
        });
    }
}