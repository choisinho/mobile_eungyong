package com.example.my_firebase_yeahn;

import static android.os.Build.ID;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class User {
    public String id;
    public String name;
    public int age;

    public User() {
    }

    //값을 추가할 때 사용
    public User(String id, String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }
    //getter, setter
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
