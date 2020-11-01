package com.example.test1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class HoaDonAdapter extends BaseAdapter {
    private Context context;
    private List<hoadon> HoaDonArrayList;
    public int layout;

    public HoaDonAdapter(Context context, List<hoadon> hoaDonArrayList, int layout) {
        this.context = context;
        HoaDonArrayList = hoaDonArrayList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return HoaDonArrayList.size();
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

        hoadon hoadon = HoaDonArrayList.get(i);
        TextView ten_User = view.findViewById(R.id.txt_TenUser);
        TextView SDT_User = view.findViewById(R.id.txt_SDT);
        TextView DiaChi_User = view.findViewById(R.id.txt_Diachi);
        TextView Soluong_hoadon = view.findViewById(R.id.txt_Soluong);
        TextView Pay_HoaDon = view.findViewById(R.id.txt_Pay);
        TextView NgayTao_HoaDon = view.findViewById(R.id.txt_NgayTao);


        ten_User.setText(""+hoadon.getTen());
        SDT_User.setText(""+hoadon.getSDT());
        DiaChi_User.setText(""+hoadon.getDiaChi());
        Soluong_hoadon.setText(""+hoadon.getSoLuong());
        Pay_HoaDon.setText("$"+hoadon.getTong());
        NgayTao_HoaDon.setText(""+hoadon.getNgay());


        return view;
    }
}
