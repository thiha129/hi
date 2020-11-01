package com.example.test1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivityQuanLyHoaDon extends AppCompatActivity {
    ListView listHoaDon;
    public static ArrayList<hoadon> arrayhoadon;
    public static HoaDonAdapter hoaDonAdapter;
    public static DatabaseLogin databaseLogin;
    int indexItem;
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
        setContentView(R.layout.activity_main_quan_ly_hoa_don);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listHoaDon = findViewById(R.id.List_HoaDon);
        databaseLogin = new DatabaseLogin(MainActivityQuanLyHoaDon.this, "mail.sqlite", null, 1);
    MainActivityQuanLyHoaDon.databaseLogin.UpData("CREATE TABLE IF NOT EXISTS HoaDon1 (Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(200), SoDienThoai VARCHAR(11), diachi VARCHAR(200),soluong integer,tong integer,Ngay VARCHAR(20))");

        arrayhoadon = new ArrayList<>();
        Upoad();
        listHoaDon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                indexItem = i;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityQuanLyHoaDon.this);
                builder.setTitle(R.string.app_name);
                builder.setMessage("Bạn có muốn xóa không?");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        databaseLogin.UpData("delete from HoaDon1 where Id = " + arrayhoadon.get(indexItem).getId() + "");
                        arrayhoadon.clear();
                        Upoad();
                        Toast.makeText(MainActivityQuanLyHoaDon.this, "Bạn đã xoá sản phẩm này ra khỏi giỏ hàng !", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.create().show();
                return false;
            }
        });

    }

    private void Upoad() {
        hoaDonAdapter = new HoaDonAdapter(MainActivityQuanLyHoaDon.this, arrayhoadon, R.layout.list_item_hoadon3);
        listHoaDon.setAdapter(hoaDonAdapter);
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
        }
        hoaDonAdapter.notifyDataSetChanged();

    }
}