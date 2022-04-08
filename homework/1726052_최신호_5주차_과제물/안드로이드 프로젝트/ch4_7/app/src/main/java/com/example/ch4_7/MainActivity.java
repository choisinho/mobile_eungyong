package com.example.ch4_7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox chkEnabled, chkClickable, chkRotate;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("연습문제 4-7");
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        chkEnabled = (CheckBox) findViewById(R.id.chk_enabled);
        chkClickable = (CheckBox) findViewById(R.id.chk_clickable);
        chkRotate = (CheckBox) findViewById(R.id.chk_rotate);
        button = (Button) findViewById(R.id.button);

        button.setEnabled(false);
        button.setClickable(false);

        chkEnabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                button.setEnabled(b);
            }
        });
        chkClickable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                button.setClickable(b);
            }
        });
        chkRotate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    button.setRotation(45);
                else
                    button.setRotation(0);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "클릭되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}