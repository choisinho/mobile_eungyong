package com.example.tapswipeview;

import androidx.annotation.GravityInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabswipeevent_activity_main);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager2 = findViewById(R.id.viewpager);

        FragAdapter fragAdapter = new FragAdapter(this);

        Frag1 f1 = new Frag1();
        Frag2 f2 = new Frag2();
        Frag3 f3 = new Frag3();

        fragAdapter.addItem(f1);
        fragAdapter.addItem(f2);
        fragAdapter.addItem(f3);

        viewPager2.setAdapter(fragAdapter);

        final List<String> tabElement = Arrays.asList("강아지", "고양이", "토끼");
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                TextView textView = new TextView(MainActivity.this);
                textView.setText(tabElement.get(position));
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(20);
                textView.setTextColor(Color.RED);
                tab.setCustomView(textView);

                List<Integer> tabImgElement = Arrays.asList(R.drawable.dog, R.drawable.cat, R.drawable.rabbit);
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(tabImgElement.get(position));
                imageView.setForegroundGravity(Gravity.CENTER);
                tab.setCustomView(imageView);
            }
        }).attach();
    }
}