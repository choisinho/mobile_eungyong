package com.example.exer12_6;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePicker date;
    EditText diary;
    Button write;
    String fileName;

    myDBHelper myHelper;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("간단 일기장(DB활용)");
        date = (DatePicker) findViewById(R.id.date);
        diary = (EditText) findViewById(R.id.diary);
        write = (Button) findViewById(R.id.write);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);

        // 데이터베이스 초기화
        myHelper = new myDBHelper(this);
        sqlDB = myHelper.getWritableDatabase();
        myHelper.onUpgrade(sqlDB, 1, 2);
        sqlDB.close();

        // 처음 실행한 날짜를 오늘로 체크하기
        fileName = year + "_" + (month + 1) + "_" + day; //연_월_일
        String str = readDiary(fileName);
        diary.setText(str);
        write.setEnabled(true);

        date.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                fileName = year + "_" + (monthOfYear+1) + "_" + dayOfMonth;
                String str = readDiary(fileName);
                diary.setText(str);
            }
        });

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (write.getText().toString() == "새로저장") {
                    sqlDB = myHelper.getWritableDatabase();
                    try {
                        sqlDB.execSQL("insert into myDiary values ( '" + fileName
                                + "' ,'" + diary.getText() + "');");
                        Toast.makeText(getApplicationContext(), "입력됨",
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e){
                        Toast.makeText(getApplicationContext(), "입력실패",
                                Toast.LENGTH_SHORT).show();
                    }
                    sqlDB.close();
                } else {
                    sqlDB = myHelper.getWritableDatabase();
                    sqlDB.execSQL("update myDiary set content = '" + diary.getText()
                            + "' where diaryDate = '" + fileName + "' ;");
                    sqlDB.close();
                    Toast.makeText(getApplicationContext(), "수정됨",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "myDB", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table if not exists myDiary " +
                    "(diaryDate char(10) primary key, content varchar(500));");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            onCreate(db);
        }
    }

    String readDiary(String fileName) {
        String diaryStr = null;
        sqlDB = myHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("select * from myDiary " +
                "where diaryDate ='"+fileName+"';",null);

        if (cursor == null) {
            diary.setHint("일기없음");
            write.setText("새로저장");
        } else {
            if (cursor.moveToFirst() == true) {
                diaryStr = cursor.getString(1);
                write.setText("수정하기");
            } else {
                diary.setHint("일기없음");
                write.setText("새로저장");
            }
        }
        cursor.close();
        sqlDB.close();
        return diaryStr;
    }
}