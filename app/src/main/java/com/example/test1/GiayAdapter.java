package com.example.test1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class GiayAdapter extends BaseAdapter {
    public Context context;
    public int layout;
    public List<Giay> giayList;

    public GiayAdapter(Context context, int layout, List<Giay> giayList) {
        this.context = context;
        this.layout = layout;
        this.giayList = giayList;
    }

    @Override
    public int getCount() {
        return giayList.size();
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

        Giay giay = giayList.get(i);
        TextView Gia = view.findViewById(R.id.tvGia);
        TextView Ten = view.findViewById(R.id.tvTen);
        TextView SoLuong = view.findViewById(R.id.tvSoLuong);

        Gia.setText(giay.getGia());
        Ten.setText(giay.getTenGiay());
        SoLuong.setText(giay.getSoluong());
        return view;
    }
}
