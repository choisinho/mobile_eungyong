package com.example.chap8_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random rdm = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button read = findViewById(R.id.read);
        Button write = findViewById(R.id.write);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileOutputStream outFs = null;
                try {
                    outFs = openFileOutput("chap8_1.txt", MODE_PRIVATE);
                    String str = "랜덤 슷지 쓰기" + rdm.nextInt(100);
                    outFs.write(str.getBytes());
                    Toast.makeText(getApplicationContext(), "쓰기 내용 : " + str, Toast.LENGTH_SHORT).show();
                    outFs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileInputStream inFs = null;
                try {
                    inFs = openFileInput("chap8_1.txt");
                    byte[] txt = new byte[100];
                    inFs.read(txt);
                    String str = new String(txt);
                    Toast.makeText(getApplicationContext(), "읽기 내용 : " + str, Toast.LENGTH_SHORT).show();
                    inFs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}