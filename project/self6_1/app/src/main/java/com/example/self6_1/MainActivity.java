package com.example.self6_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    Chronometer chrono;
    RadioButton rdoCal, rdoTime;
    DatePicker dPicker;
    TimePicker tPicker;
    TextView tvYear, tvMonth, tvDay, tvHour, tvMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.self6_1_activity_main);

        setTitle("시간 예약");
        chrono = (Chronometer) findViewById(R.id.chronometer1);
        rdoCal = (RadioButton) findViewById(R.id.rdoCal);
        rdoTime = (RadioButton) findViewById(R.id.rdoTime);
        dPicker = (DatePicker) findViewById(R.id.datePicker1);

        //만들고 있었는데 집에 가서 하라네
    }
}