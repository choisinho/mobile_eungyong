package com.example.tabswipeevent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Frag2 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View frag2 = inflater.inflate(R.layout.frag2, container, false);

        int[] idArray = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8};
        Button[] btnArray = new Button[8];

        for (int i=0; i<btnArray.length; i++)
            btnArray[i] = frag2.findViewById(idArray[i]);

        for (int i=0; i<btnArray.length; i++) {
            final int j=i;
            btnArray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "버튼 "+(j+1)+"를 클릭", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return frag2;
    }
}
