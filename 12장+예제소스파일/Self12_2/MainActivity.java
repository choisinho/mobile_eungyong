package com.example.self12_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    myDBHelper myHelper;
    EditText edtName, edtAge, edtNameResult, edtAgeResult;
    Button btnInit, btnInsert, btnUpdate, btnDelete, btnSelect;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("솔로가수 관리 DB");
        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        edtNameResult = findViewById(R.id.edtNameResult);
        edtAgeResult = findViewById(R.id.edtAgeResult);
        btnInit = findViewById(R.id.btnInit);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnSelect = findViewById(R.id.btnSelect);

        myHelper = new myDBHelper(this);

        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB, 1, 2);
                sqlDB.close();
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                try {
                    sqlDB.execSQL("insert into person values ( '"
                            + edtName.getText().toString() + "' , "
                            + edtAge.getText().toString() + ");");
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "입력실패",
                            Toast.LENGTH_SHORT).show();
                }
                sqlDB.close();
                btnSelect.callOnClick();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                if (!edtName.getText().toString().equals("")) {
                    sqlDB.execSQL("update person set age ="
                            + edtAge.getText() + " where name = '"
                            + edtName.getText().toString() + "';");
                } else
                    Toast.makeText(getApplicationContext(), "수정실패",
                            Toast.LENGTH_SHORT).show();
                sqlDB.close();
                btnSelect.callOnClick();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                if (!edtName.getText().toString().equals("")) {
                    sqlDB.execSQL("delete from person where name = '"
                            + edtName.getText().toString() + "';");
                } else
                    Toast.makeText(getApplicationContext(), "삭제실패",
                            Toast.LENGTH_SHORT).show();
                sqlDB.close();
                btnSelect.callOnClick();
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("select * from person;", null);

                String strNames = "이름" + "\r\n" + "--------" + "\r\n";
                String strAges = "나이" + "\r\n" + "--------" + "\r\n";

                while (cursor.moveToNext()) {
                    strNames += cursor.getString(0) + "\r\n";
                    strAges += cursor.getString(1) + "\r\n";
                }

                edtNameResult.setText(strNames);
                edtAgeResult.setText(strAges);

                cursor.close();
                sqlDB.close();
            }
        });
    }

    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "singleDB",null,1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table person (name text PRIMARY KEY, age integer);");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("drop table if exists person");
            onCreate(db);
            btnSelect.callOnClick();
        }
    }
}