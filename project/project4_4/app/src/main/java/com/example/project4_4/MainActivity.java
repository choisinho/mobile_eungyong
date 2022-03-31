package com.example.project4_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text1, text2;
    Switch switchAgree;
    RadioGroup rGroup1;
    RadioButton radioArray[] = new RadioButton[3];
    ImageView imgPet;
    Button btnQuit, btnRerun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("안드로이드 사진 보기");
        text1 = (TextView) findViewById(R.id.Text1);
        text2 = (TextView) findViewById(R.id.Text2);
        switchAgree = (Switch) findViewById(R.id.switchAgree);
        rGroup1 = (RadioGroup) findViewById(R.id.Rgroup1);
        radioArray[0] = (RadioButton) findViewById(R.id.Rdo80);
        radioArray[1] = (RadioButton) findViewById(R.id.Rdo90);
        radioArray[2] = (RadioButton) findViewById(R.id.Rdo100);
        imgPet = (ImageView) findViewById(R.id.ImgPet);
        btnQuit = (Button) findViewById(R.id.BtnQuit);
        btnRerun = (Button) findViewById(R.id.BtnRerun);

        switchAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (switchAgree.isChecked() == true) {
                    text2.setVisibility(View.VISIBLE);
                    rGroup1.setVisibility(View.VISIBLE);
                    imgPet.setVisibility(View.VISIBLE);
                    btnQuit.setVisibility(View.VISIBLE);
                    btnRerun.setVisibility(View.VISIBLE);
                } else {
                    text2.setVisibility(View.INVISIBLE);
                    rGroup1.setVisibility(View.INVISIBLE);
                    imgPet.setVisibility(View.INVISIBLE);
                    btnQuit.setVisibility(View.INVISIBLE);
                    btnRerun.setVisibility(View.INVISIBLE);
                }
            }
        });

        int draw[] = {R.drawable.oreo, R.drawable.pie, R.drawable.q};

        for (int i=0; i<radioArray.length; i++) {
            final int index = i;
            radioArray[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    imgPet.setImageResource(draw[index]);
                }
            });
        }

//        radioArray[0].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                imgPet.setImageResource(R.drawable.oreo);
//            }
//        });
//
//        radioArray[1].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                imgPet.setImageResource(R.drawable.pie);
//            }
//        });
//
//        radioArray[2].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                imgPet.setImageResource(R.drawable.q);
//            }
//        });

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnRerun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text2.setVisibility(View.INVISIBLE);
                rGroup1.setVisibility(View.INVISIBLE);
                imgPet.setVisibility(View.INVISIBLE);
                btnQuit.setVisibility(View.INVISIBLE);
                btnRerun.setVisibility(View.INVISIBLE);
                rGroup1.clearCheck();
                switchAgree.setChecked(false);
            }
        });
    }
}