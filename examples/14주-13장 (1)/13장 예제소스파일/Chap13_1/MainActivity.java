package com.example.chap13_1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.song1);
        Switch switch1 = findViewById(R.id.switch1);
        Button btnOn = findViewById(R.id.btnOn);
        Button btnOff = findViewById(R.id.btnOff);

//        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b)
//                    mPlayer.start();
//                else
//                    mPlayer.pause();
//            }
//        });
        switch1.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b)
                mPlayer.start();
            else
                mPlayer.pause();
        });
//        btnOn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mPlayer.seekTo(0);
//                mPlayer.start();
//            }
//        });
        btnOn.setOnClickListener(view -> {
            mPlayer.seekTo(0);
            mPlayer.start();
        });
        btnOff.setOnClickListener(view -> {
            mPlayer.pause();
        });
    }
}