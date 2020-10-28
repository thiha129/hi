package com.example.test1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NotiAdapter extends BaseAdapter {
    private Context context;
    private List<noti> NotiArrayList;
    public int layout;

    public NotiAdapter(Context context, List<noti> notiArrayList, int layout) {
        this.context = context;
        NotiArrayList = notiArrayList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return NotiArrayList.size();
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

        noti noti = NotiArrayList.get(i);
        TextView Tille = view.findViewById(R.id.txtTllte);
        TextView mESAGE = view.findViewById(R.id.txtNd);
        Tille.setText(noti.getTille());
        mESAGE.setText(noti.getMesage());
        return view;
    }
}
