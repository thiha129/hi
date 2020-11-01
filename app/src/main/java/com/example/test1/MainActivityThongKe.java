package com.example.test1;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivityThongKe extends AppCompatActivity {
    public static DatabaseLogin databaseLogin;
    public static ArrayList<hoadon> arrayhoadon;
    public static HoaDonAdapter hoaDonAdapter;
    TextView TongTien;
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
        setContentView(R.layout.activity_main_thong_ke);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseLogin = new DatabaseLogin(MainActivityThongKe.this, "mail.sqlite", null, 1);
        MainActivityThongKe.databaseLogin.UpData("CREATE TABLE IF NOT EXISTS HoaDon1 (Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(200), SoDienThoai VARCHAR(11), diachi VARCHAR(200),soluong integer,tong integer,Ngay VARCHAR(20))");
        arrayhoadon = new ArrayList<>();
        TongTien = findViewById(R.id.TongTien1);
        Loading();
    }

    private void Loading() {
        int TinhTong = 0;
        hoaDonAdapter = new HoaDonAdapter(MainActivityThongKe.this, arrayhoadon, R.layout.list_item_hoadon3);
        Cursor cursor = databaseLogin.GetData("Select * from HoaDon1 ORDER BY Id desc");
        while (cursor.moveToNext()){
            arrayhoadon.add(new hoadon(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getString(6)
            ));
//            Toast.makeText(this, ""+cursor.getString(5), Toast.LENGTH_SHORT).show();
            TinhTong += cursor.getInt(5);
//            TongTien.setText("" + hoaDonAdapter.getCount());
        }

        hoaDonAdapter.notifyDataSetChanged();
        Toast.makeText(this, ""+TinhTong, Toast.LENGTH_SHORT).show();
        TongTien.setText("$" + TinhTong);
        
    }
}