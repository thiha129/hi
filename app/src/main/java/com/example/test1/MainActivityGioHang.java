package com.example.test1;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivityGioHang extends AppCompatActivity {
    ListView listViewGioHang;
    public static ArrayList<gioHang> arraygioHang;
    public static GioHangAdapTer gioHangAdapTer;
    public static DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gio_hang);
        listViewGioHang = findViewById(R.id.GioHang);
        databaseHelper = new DatabaseHelper(MainActivityGioHang.this, "giaydep", null, 1);
        arraygioHang = new ArrayList<>();
        gioHangAdapTer = new GioHangAdapTer(MainActivityGioHang.this, arraygioHang, R.layout.list_item_giohang);
        listViewGioHang.setAdapter(gioHangAdapTer);
        int TinhTong = 0;
        Cursor cursor = databaseHelper.GetData("Select * from GioHang");
        while (cursor.moveToNext()) {
            arraygioHang.add(new gioHang(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            TinhTong += cursor.getInt(2);
        }
        Toast.makeText(this, "Tinhs toong " + TinhTong, Toast.LENGTH_SHORT).show();

    }

}