package com.example.new_gallery;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    int[] images = {R.drawable.mov11, R.drawable.mov12, R.drawable.mov13,
            R.drawable.mov14, R.drawable.mov15, R.drawable.mov16,
            R.drawable.mov17, R.drawable.mov18, R.drawable.mov19, R.drawable.mov20};

    ViewPagerAdapter vpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("이미지 뷰페이저");
        viewPager = findViewById(R.id.viewPager);

        vpAdapter = new ViewPagerAdapter(this, images);
        viewPager.setAdapter(vpAdapter);
    }
}