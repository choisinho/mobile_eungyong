package com.example.self7_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    EditText edit;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("제주도 풍경");
        edit = findViewById(R.id.edtAngle);
        image = findViewById(R.id.imageView1);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.rotate:
                String s = edit.getText().toString();
                int i = Integer.parseInt(s);
                image.setRotation(i);
                return true;
            case R.id.item1:
                image.setRotation(0);
                image.setImageResource(R.drawable.jeju2);
                item.setChecked(true);
                return true;
            case R.id.item2:
                image.setRotation(0);
                image.setImageResource(R.drawable.jeju14);
                item.setChecked(true);
                return true;
            case R.id.item3:
                image.setRotation(0);
                image.setImageResource(R.drawable.jeju6);
                item.setChecked(true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}