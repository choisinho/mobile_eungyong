package com.example.customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListItemAdapter extends BaseAdapter {
    //ListItem 타입의 ArrayList
    ArrayList<ListItem> items = new ArrayList<ListItem>();
    Context context;

    // 여기에 소스 작성....






   

    public void addItem(ListItem item){
        //ListItem 타입의 ArrayList에 아이템 추가
        items.add(item);
    }
}
