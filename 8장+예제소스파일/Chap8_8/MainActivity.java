package com.example.chap8_8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    Button readBtn;
    EditText editRaw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readBtn = findViewById(R.id.readBtn);
        editRaw = findViewById(R.id.editRaw);

        readBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    InputStream is = getResources().openRawResource(R.raw.raw_test);
                    byte[] txt = new byte[is.available()];
                    is.read(txt);
                    editRaw.setText(new String(txt));
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
