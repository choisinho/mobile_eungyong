package com.example.ch2_7;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editInput;
    Button btnAppear, btnHomepage;
    RadioGroup radioGroup;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("좀 그럴듯한 앱");
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        editInput = (EditText) findViewById(R.id.edit_input);
        btnAppear = (Button) findViewById(R.id.btn_appear);
        btnHomepage = (Button) findViewById(R.id.btn_homepage);
        radioGroup = (RadioGroup) findViewById(R.id.radio);
        imageView = (ImageView) findViewById(R.id.image);

        btnAppear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editInput.getText().toString();
                if (text.equals(""))
                    Toast.makeText(MainActivity.this, "텍스트를 입력하세요.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
        btnHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String text = editInput.getText().toString();
                    if (text.equals(""))
                        Toast.makeText(MainActivity.this, "텍스트를 입력하세요.", Toast.LENGTH_SHORT).show();
                    else {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(text));
                        startActivity(intent);
                    }
                } catch (Exception ignored) {
                    Toast.makeText(MainActivity.this, "홈페이지를 열 수 없음. 주소를 확인하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case 1:
                        imageView.setBackground(getDrawable(R.drawable.android11));
                        break;
                    case 2:
                        imageView.setBackground(getDrawable(R.drawable.android12));
                        break;
                }
            }
        });
    }
}