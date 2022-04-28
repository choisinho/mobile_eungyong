package com.example.self_viewpager2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 pager = findViewById(R.id.pager);
        MainPagerAdapter adapter = new MainPagerAdapter(this);

        Frag1 f1 = new Frag1();
        Frag2 f2 = new Frag2();
        Frag3 f3 = new Frag3();

        adapter.addItem(f1);
        adapter.addItem(f2);
        adapter.addItem(f3);

        pager.setAdapter(adapter);
    }
}