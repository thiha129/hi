package com.example.test1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NguoidungAdapter extends BaseAdapter {
    private Context context;
    private int anInt;
    private List<Nguoidung> NguoidungArrayList;
    public int layout;

    public NguoidungAdapter(Context context, int layout, List<Nguoidung> NguoidungArrayList) {
        this.context = context;
        this.NguoidungArrayList = NguoidungArrayList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return NguoidungArrayList.size();
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
        Nguoidung nguoidung = NguoidungArrayList.get(i);
        TextView id = view.findViewById(R.id.tvid);
        TextView HoVaTen = view.findViewById(R.id.tvHoVaTen);
        TextView Tendangnhap = view.findViewById(R.id.tvtendangnhap);
        TextView matkhau = view.findViewById(R.id.tvmatkhau);
        TextView ngaylap = view.findViewById(R.id.tvtngay);
        TextView Sodienthoai = view.findViewById(R.id.tvSdt);


        id.setText(nguoidung.getId());
        Tendangnhap.setText(nguoidung.getTennguoidung());
        HoVaTen.setText(nguoidung.getHovaTen());
        matkhau.setText(nguoidung.getMatkhau());
        ngaylap.setText(nguoidung.getNgaytao());
        Sodienthoai.setText(nguoidung.getSodienthoai());

        return view;
    }
}
