package com.example.chap6_10_event;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView auto1;
    MultiAutoCompleteTextView auto2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chap6_10_event_activity_main);

        setTitle("자동완성텍스트");
        auto1 = (AutoCompleteTextView) findViewById(R.id.auto1);
        auto2 = (MultiAutoCompleteTextView) findViewById(R.id.auto2);

        String[] items = {"apple", "banana", "ball", "basket", "cat", "cake", "carrot"};

        ArrayAdapter<String> array = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, items);

        auto1.setAdapter(array);
        auto2.setAdapter(array);

        MultiAutoCompleteTextView.CommaTokenizer token = new MultiAutoCompleteTextView.CommaTokenizer();
        auto2.setTokenizer(token);

        TextView textView1 = (TextView) findViewById(R.id.textView1);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        ArrayList<String> list = new ArrayList<>();

        auto1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String sel = ((TextView) view).getText().toString();
                textView1.setText("선택된 항목 : " + sel);
            }
        });
        auto2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                list.add(((TextView) view).getText().toString());
                textView2.setText("선택된 항목들 : " + list.toString());
            }
        });
    }
}