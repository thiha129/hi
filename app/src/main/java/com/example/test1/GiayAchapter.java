package com.example.test1;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GiayAchapter extends BaseAdapter {
    private Context context;
    private int anInt;
    private List<Giay> GiayArrayList;
    public int layout;

    public GiayAchapter(Context context, int layout,List<Giay> giayArrayList) {
        this.context = context;
        this.GiayArrayList = giayArrayList;
        this.layout = layout;
    }
    @Override
    public int getCount() {
        return GiayArrayList.size();
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout,null);

        Giay giay = GiayArrayList.get(i);
        ImageView image=view.findViewById(R.id.imageView);
        TextView Gia = view.findViewById(R.id.tvGia);
        TextView Ten = view.findViewById(R.id.tvTen);
        TextView SoLuong = view.findViewById(R.id.tvSoLuong);

        Gia.setText(giay.getGia());
        Ten.setText(giay.getTenGiay());
        SoLuong.setText(giay.getSoluong());
         image.setImageResource(R.drawable.ic_launcher_background);
       /*Picasso.get()
                .load("https://e.dowload.vn/data/image/2018/01/15/Android-Studio-150.png")
                .into(image);   */
        return view;
    }
}
