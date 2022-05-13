package com.example.chap7_14;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);

        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               



            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder dia = new AlertDialog.Builder(MainActivity.this);
                dia.setTitle("여기는 제목");
                dia.setMessage("이곳은 내용");
                dia.setIcon(R.drawable.ic_launcher);
                


            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String[] version = new String[] {"오레오","파이","큐(10)"};
                AlertDialog.Builder dia = new AlertDialog.Builder(MainActivity.this);
                dia.setTitle("좋아하는 버전은?");
                dia.setIcon(R.drawable.ic_launcher);
                


            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String[] version = new String[]{"오레오", "파이", "큐(10)"};
                AlertDialog.Builder dia = new AlertDialog.Builder(MainActivity.this);
                dia.setTitle("좋아하는 버전은?");
                dia.setIcon(R.drawable.ic_launcher);
                


            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String[] version = new String[] {"오레오", "파이", "큐(10)"};
                final boolean[] check = new boolean[] {true,false,false};

                AlertDialog.Builder dia = new AlertDialog.Builder(MainActivity.this);
                dia.setTitle("좋아하는 버전은?");
                dia.setIcon(R.drawable.ic_launcher);
                


            }
        });
    }
}