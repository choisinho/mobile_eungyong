package com.example.self10_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Second 액티비티");

        Intent intent= getIntent();
        String calc = (intent.getStringExtra("Calc"));
        int calValue = 0;

        if (calc.equals("+")) {
            calValue = intent.getIntExtra("Num1", 0) + intent.getIntExtra("Num2", 0);
        }
    }
}