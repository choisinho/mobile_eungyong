package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ListItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("전화번호부");
        listView = findViewById(R.id.listview);
        adapter = new ListItemAdapter();

        int[] imgArr = //이미지 데이터 배열
                new int[]{R.drawable.icon1,R.drawable.icon2,R.mipmap.ic_launcher,
                          R.drawable.icon1,R.drawable.icon2,R.mipmap.ic_launcher,
                          R.drawable.icon1,R.drawable.icon2,R.mipmap.ic_launcher};
        String[] nameArr = //이름 데이터 배열
                new String[]{"홍길동","김길동","박길동",
                             "최길동","양길동","우길동",
                             "송길동","성길동","소길동",};
        String[] phoneArr = //전화번호 데이터 배열
                new String[]{"010-1234-1234","010-2345-2345","010-3456-3456",
                             "010-1234-1234","010-2345-2345","010-3456-3456",
                             "010-1234-1234","010-2345-2345","010-3456-3456"};

        /* ListItemAdapter를 통해
           ListItem 구조의 데이터 저장 리스트에 항목을 추가 */
        // 여기에 소스 작성....





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 여기에 소스 작성....




            }
        });
    }
}