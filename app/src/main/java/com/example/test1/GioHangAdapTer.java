package com.example.test1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class GioHangAdapTer extends BaseAdapter {
    private Context context;
    private List<gioHang> GioHangArrayList;
    public int layout;

    public GioHangAdapTer(Context context, List<gioHang> gioHangArrayList, int layout) {
        this.context = context;
        GioHangArrayList = gioHangArrayList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return GioHangArrayList.size();
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


        gioHang gioHang = GioHangArrayList.get(i);

        TextView ten_giohang = view.findViewById(R.id.Ten_GioHang);
        TextView gia_giohang = view.findViewById(R.id.Gia_GioHang);
        ten_giohang.setText(gioHang.getTen());
        gia_giohang.setText(""+gioHang.getGia());

        return view;
    }
}
