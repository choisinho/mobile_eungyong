package com.example.chap8_10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MODE_PRIVATE);

        Button read = findViewById(R.id.read);
        final EditText edit = findViewById(R.id.edit);

        read.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    FileInputStream inFs = new FileInputStream("/sdcard/download/test.txt");
                    byte[] txt = new byte[inFs.available()];
                    inFs.read(txt);
                    edit.setText(new String(txt));
                    inFs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}