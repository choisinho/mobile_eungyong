package com.example.chap6_10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView auto1;
    MultiAutoCompleteTextView auto2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chap6_10_activity_main);

        setTitle("자동완성텍스트");
        auto1 = (AutoCompleteTextView) findViewById(R.id.auto1);
        auto2 = (MultiAutoCompleteTextView) findViewById(R.id.auto2);

        String[] items = {"apple", "banana", "ball", "basket", "cat", "cake", "carrot"};

        ArrayAdapter<String> array = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, items);

        auto1.setAdapter(array);
        auto2.setAdapter(array);

        MultiAutoCompleteTextView.CommaTokenizer token = new MultiAutoCompleteTextView.CommaTokenizer();
        auto2.setTokenizer(token);
    }
}