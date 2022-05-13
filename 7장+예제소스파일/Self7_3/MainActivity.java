package com.example.self7_3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, email;
    TextView toast;
    Button btn;
    EditText editName, editEmail;
    View dialogView, toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("사용자 정보 입력");
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogView = View.inflate(MainActivity.this,
                        R.layout.dialog, null);
                AlertDialog.Builder dia =
                        new AlertDialog.Builder(MainActivity.this);
                dia.setTitle("사용자 정보 입력");
                dia.setIcon(R.drawable.ic_menu_allfriends);
                dia.setView(dialogView);

                dia.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                editName = dialogView.findViewById(R.id.editName);
                                editEmail = dialogView.findViewById(R.id.editEmail);
                                name.setText(editName.getText());
                                email.setText(editEmail.getText());
                            }
                        });

                dia.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast tos = new Toast(MainActivity.this);
                                toastView = View.inflate(MainActivity.this,
                                        R.layout.toast, null);
                                toast = toastView.findViewById(R.id.toast);
                                toast.setText("취소했습니다.");
                                tos.setView(toastView);
                                tos.show();
                            }
                        });
                dia.show();
            }
        });
    }
}