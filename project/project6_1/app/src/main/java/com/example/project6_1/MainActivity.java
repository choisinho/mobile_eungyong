package com.example.project6_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    Chronometer chrono;
    Button btnStart, btnEnd;
    RadioButton cal, time;
    CalendarView calView;
    TimePicker tPicker;
    TextView tvYear, tvMonth, tvDay, tvHour, tvMinute;
    int seleYear, seleMonth, seleDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project6_1_activity_main);

        setTitle("시간 예약");
        btnStart = (Button) findViewById(R.id.btnStart);
        btnEnd = (Button) findViewById(R.id.btnEnd);
        chrono = (Chronometer) findViewById(R.id.chrono);
        cal = (RadioButton) findViewById(R.id.cal);
        time = (RadioButton) findViewById(R.id.time);
        tPicker = (TimePicker) findViewById(R.id.timePick);
        calView = (CalendarView) findViewById(R.id.calView);
        tvYear = (TextView) findViewById(R.id.year);
        tvMonth = (TextView) findViewById(R.id.month);
        tvDay = (TextView) findViewById(R.id.day);
        tvHour = (TextView) findViewById(R.id.hour);
        tvMinute = (TextView) findViewById(R.id.min);

        tPicker.setVisibility(View.INVISIBLE);
        calView.setVisibility(View.INVISIBLE);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tPicker.setVisibility(View.INVISIBLE);
                calView.setVisibility(View.VISIBLE);
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tPicker.setVisibility(View.VISIBLE);
                calView.setVisibility(View.INVISIBLE);
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();
                chrono.setTextColor(Color.RED);
            }
        });
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.stop();
                chrono.setTextColor(Color.BLUE);
                tvYear.setText("" + seleYear);
                tvMonth.setText("" + seleMonth);
                tvDay.setText("" + seleDay);
                tvHour.setText("" + tPicker.getHour());
                tvMinute.setText("" + tPicker.getMinute());
            }
        });
        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                seleYear = i;
                seleMonth = i1 + 1; //월 데이터가 0부터 시작해서 1을 더함
                seleDay = i2;
            }
        });
    }
}