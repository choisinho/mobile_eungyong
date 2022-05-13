package com.example.chap7_14;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.UiAutomation;
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
               AlertDialog.Builder dia = new AlertDialog.Builder(MainActivity.this);
               dia.setTitle("여기는 제목");
               dia.setMessage("이곳은 내용");
               dia.setIcon(R.drawable.ic_launcher);
               dia.setPositiveButton("확인", null);
               dia.show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder dia = new AlertDialog.Builder(MainActivity.this);
                dia.setTitle("여기는 제목");
                dia.setMessage("이곳은 내용");
                dia.setIcon(R.drawable.ic_launcher);
                dia.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "확인을 누름", Toast.LENGTH_SHORT).show();
                    }
                });
                dia.show();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String[] version = new String[] {"오레오","파이","큐(10)"};
                AlertDialog.Builder dia = new AlertDialog.Builder(MainActivity.this);
                dia.setTitle("좋아하는 버전은?");
                dia.setIcon(R.drawable.ic_launcher);
                dia.setItems(version, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        btn3.setText(version[i]);
                    }
                });
                dia.setPositiveButton("닫기", null);
                dia.show();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String[] version = new String[]{"오레오", "파이", "큐(10)"};
                AlertDialog.Builder dia = new AlertDialog.Builder(MainActivity.this);
                dia.setTitle("좋아하는 버전은?");
                dia.setIcon(R.drawable.ic_launcher);
                dia.setSingleChoiceItems(version, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        btn4.setText(version[i]);
                    }
                });
                dia.setPositiveButton("닫기", null);
                dia.show();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String[] version = new String[] {"오레오", "파이", "큐(10)"};
                final boolean[] check = new boolean[] {true,false,false};

                AlertDialog.Builder dia = new AlertDialog.Builder(MainActivity.this);
                dia.setTitle("좋아하는 버전은?");
                dia.setIcon(R.drawable.ic_launcher);
                dia.setMultiChoiceItems(version, check, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        btn5.setText(version[i]);
                    }
                });
                dia.setPositiveButton("닫기", null);
                dia.show();
            }
        });
    }
}