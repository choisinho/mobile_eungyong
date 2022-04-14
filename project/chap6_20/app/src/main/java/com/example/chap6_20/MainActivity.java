package com.example.chap6_20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bt1, bt2, bt3;
    FragmentManager fm;
    FragmentTransaction tran;
    Frag1 frag1;
    Frag2 frag2;
    Frag3 frag3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chap6_20_activity_main);

        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);

        frag1 = new Frag1();
        frag2 = new Frag2();
        frag3 = new Frag3();

        fm = getSupportFragmentManager();

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tran = fm.beginTransaction();
                tran.replace(R.id.main_frame, frag1);
                tran.commit();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tran = fm.beginTransaction();
                tran.replace(R.id.main_frame, frag2);
                tran.commit();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tran = fm.beginTransaction();
                tran.replace(R.id.main_frame, frag3);
                tran.commit();
            }
        });
    }
}