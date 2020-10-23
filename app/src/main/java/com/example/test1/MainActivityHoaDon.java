package com.example.test1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivityHoaDon extends AppCompatActivity {
    ListView listViewDonHang;
    public static ArrayList<gioHang> arraygioHang;
    public static GioHangAdapTer gioHangAdapTer;
    public static DatabaseHelper databaseHelper;
    public static DatabaseLogin databaseLogin;
    TextView Payy;
    TextView dt,sdt,ten;

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hoa_don);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseHelper = new DatabaseHelper(MainActivityHoaDon.this, "giaydep", null, 1);
        databaseLogin = new DatabaseLogin(MainActivityHoaDon.this, "mail.sqlite", null, 1);
        MainActivityHoaDon.databaseLogin.UpData("CREATE TABLE IF NOT EXISTS TaiKhoan3 (Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(200), Pass VARCHAR(200), Hovaten VARCHAR(200), SoDienThoai VARCHAR(11),Ngay VARCHAR(20), diachi VARCHAR(200))");

        arraygioHang = new ArrayList<>();
        AnhXa();
        InfoUser();
    }

    private void InfoUser() {
        dt= findViewById(R.id.edt_Address);
        sdt = findViewById(R.id.edt_number);
        ten = findViewById(R.id.edt_NameUser);
        Intent i = getIntent();
        int Id = i.getIntExtra("IdUser", 1);
        Cursor cursor = databaseLogin.GetData("SELECT * FROM TaiKhoan3 where Id = " + Id + "");
       while (cursor.moveToNext()){
           String TenMoi = cursor.getString(3);
           ten.setText(TenMoi);
           String NewNumber = cursor.getString(4);
           sdt.setText(NewNumber);
           String NewAdd = cursor.getString(6);
           dt.setText(NewAdd);
       }
    }

    private void AnhXa() {
        Payy = findViewById(R.id.Pay);
        listViewDonHang = findViewById(R.id.lishoadon);
        int TinhTong = 0;
        gioHangAdapTer = new GioHangAdapTer(MainActivityHoaDon.this, arraygioHang, R.layout.list_item_giaodiengiohang);
        listViewDonHang.setAdapter(gioHangAdapTer);
        Cursor cursor = databaseHelper.GetData("Select * from GioHang2");
        while (cursor.moveToNext()) {
            arraygioHang.add(new gioHang(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getString(4))
            );
            TinhTong += cursor.getInt(2);
//            Cont.setText("" + gioHangAdapTer.getCount());
        }
//        Sum.setText("$" + TinhTong);
        int tongtien = TinhTong + 10;
        Payy.setText("$" + tongtien);
    }
}