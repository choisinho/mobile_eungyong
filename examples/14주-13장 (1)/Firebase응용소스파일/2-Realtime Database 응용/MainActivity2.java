package com.example.my_firebase_yeahn;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {
    // database connection
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    EditText idEdit, ageEdit, nameEdit;
    String idString = "", nameString = "";
    int ageInt;
    Boolean flag = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.setTitle("Firebase Realtime Database");

        idEdit = findViewById(R.id.idEdit);
        ageEdit = findViewById(R.id.ageEdit);
        nameEdit = findViewById(R.id.nameEdit);
        Button regiBtn = findViewById(R.id.regiBtn);
        Button searchBtn = findViewById(R.id.searchBtn);
        Button deleBtn = findViewById(R.id.deleBtn);

        //신규 등록 이벤트
        regiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEditData(); //입력 데이터 가져오기
                if (flag == false)
                    return;
                else {
                    flag = false;
                    User user = new User(idString, nameString, ageInt);
                    //Firebase에 데이터 등록
                    myRef.child("users").child(idString).setValue(user);
                    Toast.makeText(MainActivity2.this,
                            "데이터 등록 완료!!!", Toast.LENGTH_SHORT).show();
                    clearEdit();
                }
            }
        });
        //번호 조회 이벤트
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIdState();
                if (flag == false)
                    return;
                else {
                    myRef.child("users").child(idString).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            User user = snapshot.getValue(User.class);
                            if(user == null) {
                                Toast.makeText(MainActivity2.this,
                                        "존재하지 않는 Id", Toast.LENGTH_SHORT).show();
                                clearEdit();
                                return;
                            } else {
                                if(flag==false)
                                    return;
                                else {
                                    nameEdit.setText(user.getName());
                                    nameEdit.setTextColor(Color.BLUE);
                                    ageEdit.setText(user.getAge()+"");
                                    ageEdit.setTextColor(Color.BLUE);
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        });

        //자료 삭제 이벤트
        deleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIdState();
                if (flag == false)
                    return;
                else {
                    //id 기준으로 데이터 삭제
                    myRef.child("users").child(idString).removeValue();
                    Toast.makeText(MainActivity2.this,
                            "자료 삭제 완료!!!", Toast.LENGTH_SHORT).show();
                    clearEdit();
                }
            }
        });
    }

    //필수 입력 데이터 체크
    public void getEditData() {
        idString = idEdit.getText().toString().trim();
        nameString = nameEdit.getText().toString().trim();
        String numString = ageEdit.getText().toString().trim();

        if (idString.isEmpty() | nameString.isEmpty() | numString.isEmpty()) {
            flag = false;
            Toast.makeText(MainActivity2.this,
                    "아이디,이름,나이 필수 입력!!!", Toast.LENGTH_SHORT).show();
        } else {
            flag = true;
            ageInt = Integer.parseInt(numString);
        }
    }

    //입력 아이디 체크
    public void getIdState() {
        idString = idEdit.getText().toString().trim();
        if (idString.isEmpty()) {
            flag = false;
            Toast.makeText(MainActivity2.this,
                    "아이디 필수 입력!!!", Toast.LENGTH_SHORT).show();
        } else
            flag = true;
    }

    //입력 창 리셋
    public void clearEdit() {
        idEdit.setText("");
        nameEdit.setText("");
        ageEdit.setText("");
    }
}