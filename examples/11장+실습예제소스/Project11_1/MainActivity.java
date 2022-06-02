package com.example.project11_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("그리드뷰 영화 포스터");
        GridView gv = findViewById(R.id.gridView1);

        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);
    }

    public class MyGridAdapter extends BaseAdapter {
        Integer[] posterID =
                      { R.drawable.mov01, R.drawable.mov02, R.drawable.mov03,
                        R.drawable.mov04, R.drawable.mov05, R.drawable.mov06,
                        R.drawable.mov07, R.drawable.mov08, R.drawable.mov09,
                        R.drawable.mov10, R.drawable.mov01, R.drawable.mov02,
                        R.drawable.mov03, R.drawable.mov04, R.drawable.mov05,
                        R.drawable.mov06, R.drawable.mov07, R.drawable.mov08,
                        R.drawable.mov09, R.drawable.mov10, R.drawable.mov01,
                        R.drawable.mov02, R.drawable.mov03, R.drawable.mov04,
                        R.drawable.mov05, R.drawable.mov06, R.drawable.mov07,
                        R.drawable.mov08, R.drawable.mov09, R.drawable.mov10 };

        Context context;
        public MyGridAdapter(Context c) {
            context = c;
        }
        @Override
        public int getCount() {
            return posterID.length;
        }
        @Override
        public Object getItem(int i) {
            return null;
        }
        @Override
        public long getItemId(int i) {
            return 0;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new GridView.LayoutParams(200, 300));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setPadding(5, 5, 5, 5);
            imageview.setImageResource(posterID[i]);

            int pos = i;
            imageview.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    View dialogView = View.inflate(MainActivity.this,
                                                R.layout.dialog, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(
                            MainActivity.this);
                    ImageView ivPoster = (ImageView) dialogView
                            .findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(posterID[pos]);
                    dlg.setTitle("큰 포스터");
                    dlg.setIcon(R.drawable.ic_launcher);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기", null);
                    dlg.show();
                }
            });
            return imageview;
        }
    }
}