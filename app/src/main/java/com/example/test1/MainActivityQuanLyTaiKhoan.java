package com.example.test1;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivityQuanLyTaiKhoan extends AppCompatActivity {
    public static ArrayList<Nguoidung> nguoidungArrayList;
    public static NguoidungAdapter adapter;
    public static DatabaseLogin databaseLogin;
    ListView listView;
    TextView showTaikhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quan_ly_tai_khoan);
        listView = findViewById(R.id.listnguoidung);
        nguoidungArrayList = new ArrayList<>();
        databaseLogin = new DatabaseLogin(this, "mail.sqlite", null, 1);
        databaseLogin.UpData("CREATE TABLE IF NOT EXISTS TaiKhoan2 (Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(200), Pass VARCHAR(200), Hovaten VARCHAR(200), SoDienThoai VARCHAR(11),Ngay VARCHAR(20))");
        abc02();
    }

    private void abc02() {
        showTaikhoan = findViewById(R.id.SoluongTaiKhoan);
        adapter = new NguoidungAdapter(MainActivityQuanLyTaiKhoan.this, R.layout.list_item_abc1, nguoidungArrayList);
        Cursor cursor = databaseLogin.GetData("SELECT * FROM TaiKhoan2");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String HovaTen = cursor.getString(1);
                String TendangNhap = cursor.getString(2);
                String MatKhau = cursor.getString(4);
                String SoDienThoai = cursor.getString(3);
                String Ngaytao = cursor.getString(5);
                nguoidungArrayList.add(new Nguoidung(id, HovaTen, TendangNhap, MatKhau, SoDienThoai, Ngaytao));
            }
            listView.setAdapter(adapter);
        } else {
            Toast.makeText(MainActivityQuanLyTaiKhoan.this, "NOT OK", Toast.LENGTH_SHORT).show();
        }
        showTaikhoan.setText("Số tài khoản hiện đã đăng kí:"+adapter.getCount());

    }
}