package com.example.project5_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edit1, edit2;
    Button add, sub, mul, div;
    TextView txtRes;

    String num1, num2;
    int result;
    int i;

    Button[] numBtn = new Button[10];
    int[] btnIDs = {R.id.Btn0, R.id.Btn1, R.id.Btn2, R.id.Btn3, R.id.Btn4, R.id.Btn5, R.id.Btn6, R.id.Btn7, R.id.Btn8, R.id.Btn9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project5_2_activity_main);

        setTitle("테이블레이아웃 계산기");
        edit1 = (EditText) findViewById(R.id.Edit1);
        edit2 = (EditText) findViewById(R.id.Edit2);
        add = (Button) findViewById(R.id.Add);
        sub = (Button) findViewById(R.id.Sub);
        mul = (Button) findViewById(R.id.Mul);
        div = (Button) findViewById(R.id.Div);
        txtRes = (TextView) findViewById(R.id.Result);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Integer.parseInt(num1) + Integer.parseInt(num2);
                txtRes.setText("계산결과 : " + result);
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Integer.parseInt(num1) - Integer.parseInt(num2);
                txtRes.setText("계산결과 : " + result);
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Integer.parseInt(num1) * Integer.parseInt(num2);
                txtRes.setText("계산결과 : " + result);
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Integer.parseInt(num1) / Integer.parseInt(num2);
                txtRes.setText("계산결과 : " + result);
            }
        });

        for (i = 0; i < numBtn.length; i++)
            numBtn[i] = (Button) findViewById(btnIDs[i]);

        for (i = 0; i < numBtn.length; i++) {
            int index = i;
            numBtn[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (edit1.isFocused()) {
                        num1 = edit1.getText().toString() + numBtn[index].getText().toString();
                        edit1.setText(num1);
                    } else if (edit2.isFocused()) {
                        num2 = edit2.getText().toString() + numBtn[index].getText().toString();
                        edit2.setText(num2);
                    }
                }
            });
        }
    }
}