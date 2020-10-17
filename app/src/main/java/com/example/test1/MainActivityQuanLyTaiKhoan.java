package com.example.test1;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivityQuanLyTaiKhoan extends AppCompatActivity {
    public static ArrayList<Nguoidung> nguoidungArrayList;
    public static NguoidungAdapter adapter;
    private DatabaseHelper databaseHelper;
    public static DatabaseLogin databaseLogin;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quan_ly_tai_khoan);
        listView=findViewById(R.id.listnguoidung);
//abc02();
    }
    private void abc02() {
        adapter = new NguoidungAdapter(MainActivityQuanLyTaiKhoan.this, R.layout.list_item_abc, nguoidungArrayList);
        Cursor cursor = MainActivityDangKy.databaseLogin.GetData("SELECT * FROM TaiKhoan2");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String HovaTen = cursor.getString(1);
                String TendangNhap = cursor.getString(2);
                String MatKhau = cursor.getString(3);
                String SoDienThoai = cursor.getString(4);
                String Ngaytao = cursor.getString(5);
                nguoidungArrayList.add(new Nguoidung(id, HovaTen, TendangNhap, MatKhau, SoDienThoai, Ngaytao));
            }
            listView.setAdapter(adapter);
        } else {
            Toast.makeText(MainActivityQuanLyTaiKhoan.this, "NOT OK", Toast.LENGTH_SHORT).show();
        }

    }
}