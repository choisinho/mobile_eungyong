package com.example.new_gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter {
    Context context;
    int[] images;
    LayoutInflater layoutInflater;

    public ViewPagerAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = layoutInflater.inflate(R.layout.item, container, false);
        ImageView imageView = (ImageView) layoutInflater.inflate(R.layout.item, container, false);
        imageView.setImageResource(images[position]);
        container.addView(itemView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "선택된 포스터는 image " + (position + 1),
                        Toast.LENGTH_LONG).show();
            }
        });
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container,
                            int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
