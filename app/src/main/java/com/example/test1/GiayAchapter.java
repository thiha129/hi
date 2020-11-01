package com.example.test1;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class GiayAchapter extends BaseAdapter {
    private Context context;
    private List<Giay> GiayArrayList;
    public int layout;
    public GiayAchapter(Context context, int layout, List<Giay> giayArrayList) {
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
        view = inflater.inflate(layout, null);

        final Giay giay = GiayArrayList.get(i);
        final ImageView image = view.findViewById(R.id.imageView);

        TextView Ten = view.findViewById(R.id.tvTen);
        TextView SoLuong = view.findViewById(R.id.tvSoLuong);
//        TextView Gia = view.findViewById(R.id.tvGia);
        TextView gia = view.findViewById(R.id.tvGia);

        Ten.setText(giay.getTenGiay());
        SoLuong.setText(giay.getSoluong());
//        Gia.setText(giay.getGia());
        gia.setText(""+giay.getGia());
       Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Picasso.get()
                        .load(giay.getLinkAnh())
                        .into(image);
            }
        },1000);

        return view;
    }
}
