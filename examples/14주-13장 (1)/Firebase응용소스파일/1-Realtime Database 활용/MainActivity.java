package com.example.my_firebase_yeahn;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("myMessage");
        myRef.setValue("안녕...데이터베이스...");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "읽어온 값 : " + value);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "읽기 실패...", error.toException());
            }
        });

        // 데이터 구조 및 쓰기 방법 이해하기
        DatabaseReference testRef = FirebaseDatabase.getInstance().getReference("students");

        testRef.child("sub1").setValue("학생1");
        testRef.child("sub2").setValue("학생2");
        testRef.child("sub3").setValue("학생3");

        testRef.child("학생1").child("id").setValue("id1");
        testRef.child("학생1").child("pswd").setValue("pswd1");

        testRef.child("학생2").child("id").setValue("id2");
        testRef.child("학생2").child("pswd").setValue("pswd2");

        testRef.child("학생3").child("id").setValue("id3");
        testRef.child("학생3").child("pswd").setValue("pswd3");
    }
}
