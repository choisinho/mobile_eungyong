package com.example.project8_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText diary;
    Button write;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("간단한 일기장");
        DatePicker date = findViewById(R.id.date);
        diary = findViewById(R.id.diary);
        write = findViewById(R.id.write);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);

        // 처음 실행시에 실행할 내용
        fileName = year + "_" + (month+1) + "_" + day + ".txt";
        String str = readDiary(fileName);
        diary.setText(str);
        write.setEnabled(true);
        write.setText("새로저장");

        date.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker,
                                      int year, int monthOfYear, int dayOfMonth) {
                fileName = year + "_" + (monthOfYear + 1) + "_" +
                                dayOfMonth + ".txt"; //연_월_일.txt
                String str = readDiary(fileName);
                diary.setText(str);
                write.setEnabled(true);
            }
        });

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream outFs = openFileOutput(fileName, MODE_PRIVATE);
                    String str = diary.getText() + "";
                    outFs.write(str.getBytes());
                    outFs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),fileName+"이 저장됨",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    String readDiary(String fileName) {
        String diaryStr = null;
        byte[] txt = new byte[500];

        FileInputStream inFs = null;
        try {
            inFs = openFileInput(fileName);
            inFs.read(txt);
            inFs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        diaryStr = (new String(txt)).trim();
        diary.setHint("일기 없음");
        write.setText("새로저장");

        return diaryStr;
    }
}