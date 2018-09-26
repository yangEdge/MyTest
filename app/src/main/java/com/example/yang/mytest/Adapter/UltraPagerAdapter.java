package com.example.yang.mytest.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.yang.mytest.R;

public class UltraPagerAdapter extends PagerAdapter {
    private Context context;
    private int[] imageArr;

    public UltraPagerAdapter(Context context, int[] imageArr) {
        this.context = context;
        this.imageArr = imageArr;
    }

    @Override
    public int getCount() {
        return imageArr.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.my_view_pager_item,null);
        ImageView img = v.findViewById(R.id.img);
        img.setImageResource(imageArr[position]);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        LinearLayout view = (LinearLayout) object;
        container.removeView(view);
    }
}
