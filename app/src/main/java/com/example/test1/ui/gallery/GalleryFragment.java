package com.example.test1.ui.gallery;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.test1.DatabaseHelper;
import com.example.test1.Giay;
import com.example.test1.GiayAchapter;
import com.example.test1.LoadingDialog;
import com.example.test1.MainActivityThongTinSanPham;
import com.example.test1.R;

import java.util.ArrayList;

public class GalleryFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {

    private GalleryViewModel galleryViewModel;
    public static DatabaseHelper databaseHelper;
    GridView gridView;
    public static ArrayList<Giay> arrayDoVat;
    public static GiayAchapter adapter;
    TextView loc;
    private int indext = -1;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        gridView = root.findViewById(R.id.lv1);
        databaseHelper = new DatabaseHelper(getActivity(), "giaydep", null, 1);
        databaseHelper.UpData("CREATE TABLE IF NOT EXISTS Sanpham2(Id INTEGER PRIMARY KEY AUTOINCREMENT,Ten VarChar(150), Gia VarChar(150), SoLuong VarChar(150), LinkAnh Text ,Chitiet VarChar(150), size VarChar(150))");
        arrayDoVat = new ArrayList<>();
        adapter = new GiayAchapter(getActivity(), R.layout.list_item_abc, arrayDoVat);
        loc = root.findViewById(R.id.textView12);
        xyz();
        Loc();
         abc4();

        return root;
    }

    private void Loc() {
        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });
    }

    public void showPopup(View view) {
        PopupMenu popup = new PopupMenu(getActivity(), view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.item1:
                final LoadingDialog loadingDialog = new LoadingDialog(getActivity());
                loadingDialog.startLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                        LowToHight();
                    }
                },2000);
                return true;
            case R.id.item2:
                final LoadingDialog loadingDialog1 = new LoadingDialog(getActivity());
                loadingDialog1.startLoadingDialog();
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog1.dismissDialog();
                        HightToLow();
                    }
                },2000);
                return true;
            case R.id.item3:
                final LoadingDialog loadingDialog2 = new LoadingDialog(getActivity());
                loadingDialog2.startLoadingDialog();
                Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog2.dismissDialog();
//                        xyz();
                    }
                },2000);
                return true;
            default:
                return false;
        }
    }

    private void HightToLow() {
        Cursor cursor = databaseHelper.GetData("SELECT * FROM Sanpham2 WHERE Ten LIKE '%Nike%' ORDER BY Gia DESC");
        arrayDoVat.clear();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String TenGiay = cursor.getString(1);
                String Gia = cursor.getString(2) + "$";
                String Soluong = cursor.getString(3);
                String LinkAnh = cursor.getString(4);
                String Chitiet = cursor.getString(5);

                arrayDoVat.add(new Giay(id, TenGiay, Gia, Soluong, LinkAnh, Chitiet));
            }

            adapter.notifyDataSetChanged();
            gridView.setAdapter(adapter);
        }
    }

    private void LowToHight() {
        Cursor cursor = databaseHelper.GetData("SELECT * FROM Sanpham2 WHERE Ten LIKE '%Nike%' ORDER BY Gia ASC");
        arrayDoVat.clear();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String TenGiay = cursor.getString(1);
                String Gia = cursor.getString(2) + "$";
                String Soluong = cursor.getString(3);
                String LinkAnh = cursor.getString(4);
                String Chitiet = cursor.getString(5);

                arrayDoVat.add(new Giay(id, TenGiay, Gia, Soluong, LinkAnh, Chitiet));
            }

            adapter.notifyDataSetChanged();
            gridView.setAdapter(adapter);
        }
//        restartActivity(getActivity());
    }

    private void xyz() {
        adapter = new GiayAchapter(getActivity(), R.layout.list_item_abc, arrayDoVat);
        final Cursor cursor = databaseHelper.GetData("SELECT * FROM Sanpham2 where Ten LIKE '%Nike%'  ");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String TenGiay = cursor.getString(1);
                String Gia = cursor.getString(2) + "$";
                String Soluong = cursor.getString(3);
                String LinkAnh = cursor.getString(4);
                String Chitiet = cursor.getString(5);

                arrayDoVat.add(new Giay(id, TenGiay, Gia, Soluong, LinkAnh, Chitiet));
            }
            gridView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
    private void abc4() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                indext = i;
                Intent movetocharacter = new Intent(getActivity(), MainActivityThongTinSanPham.class);
                movetocharacter.putExtra("id", arrayDoVat.get(i).getId());
                startActivity(movetocharacter);
            }
        });
    }
    private void restartActivity(Activity act) {
        Intent intent = new Intent();
        intent.setClass(act, act.getClass());
        act.startActivity(intent);
        act.finish();
    }
}