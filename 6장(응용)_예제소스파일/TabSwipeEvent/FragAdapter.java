package com.example.tabswipeevent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class FragAdapter extends FragmentStateAdapter {
    ArrayList<Fragment> items = new ArrayList<Fragment>();

    public FragAdapter(FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }
    public void addItem(Fragment item){
        items.add(item);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return items.get(position);
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
}
