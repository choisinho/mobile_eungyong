package com.example.project12_2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.NoCopySpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//import android.widget.Toast;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {
    myDBHelper myHelper;
    EditText edtName, edtNumber, edtNameResult, edtNumberResult;
    Button btnInit, btnInsert, btnSelect;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("가수 그룹 관리 DB");
        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);
        edtNameResult = findViewById(R.id.edtNameResult);
        edtNumberResult = findViewById(R.id.edtNumberResult);
        btnInit = findViewById(R.id.btnInit);
        btnInsert = findViewById(R.id.btnInsert);
        btnSelect = findViewById(R.id.btnSelect);

        myHelper = new myDBHelper(this);
        //sqlDB = myHelper.getWritableDatabase();

        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB,1,2);
//                sqlDB.close();
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();

                try {
                    sqlDB.execSQL("insert into groupTBL values ('"
                            + edtName.getText().toString() + "' , "
                            + edtNumber.getText().toString() + ");");
                } catch (Exception e){
                    makeText(getApplicationContext(),"입력실패",
                            Toast.LENGTH_SHORT).show();
                }
//                sqlDB.close();
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("select * from groupTBL;",null);

                String strNames = "그룹이름"+"\r\n"+"--------"+"\r\n";
                String strNumbers = "인원"+"\r\n"+"--------"+"\r\n";

                while (cursor.moveToNext()){
                    strNames += cursor.getString(0) + "\r\n";
                    strNumbers += cursor.getString(1)+"\r\n";
                }
                edtNameResult.setText(strNames);
                edtNumberResult.setText(strNumbers);

                cursor.close();
//                sqlDB.close();
            }
        });
    }

    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(@Nullable Context context) {
            super(context, "groupDB", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table groupTBL(gName char(20) primary key, gNumber integer);");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists groupTBL");
            onCreate(db);
        }
    }
}