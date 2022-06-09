package com.example.my_firebase_yeahn;

public class UserImage {
    public String id;
    public String name;
    public int age;
    public String imgPath;

    public UserImage() { }

    //값을 추가할 때 사용
    public UserImage(String id, String name, int age, String imgPath){
        this.id = id;
        this.name = name;
        this.age = age;
        this.imgPath = imgPath;
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
    public String getImgPath() {
        return imgPath;
    }
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
