package com.example.customlistview;

public class ListItem { // 데이터 항목의 구조
    private int img;
    private String name;
    private String phone;

    public ListItem(int img, String name, String phone) {
        this.img = img;
        this.name = name;
        this.phone = phone;
    }
    public int getImg() {
        return img;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
}
