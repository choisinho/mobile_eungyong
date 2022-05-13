package com.example.project7_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout baseLayout;
    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("배경색바꾸기(컨텍스트 메뉴)");
        baseLayout = (LinearLayout)findViewById(R.id.baseLayout);
        btn1 = (Button) findViewById(R.id.btn1);
        registerForContextMenu(btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        registerForContextMenu(btn2);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();
        if (v==btn1) {
            menu.setHeaderTitle("배경색 변경");
            menuInflater.inflate(R.menu.menu1, menu);
        }
        if (v == btn2) {
            menuInflater.inflate(R.menu.menu2, menu);
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.red:
                baseLayout.setBackgroundColor(Color.RED);
                return true;
            case R.id.green:
                baseLayout.setBackgroundColor(Color.GREEN);
                return true;
            case R.id.blue:
                baseLayout.setBackgroundColor(Color.BLUE);
                return true;
            case R.id.rotate:
                btn2.setRotation(45);
                return true;
            case R.id.large:
                btn2.setScaleX(2);
                return true;
            case R.id.origin:
                btn2.setRotation(0);
                btn2.setScaleX(1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}