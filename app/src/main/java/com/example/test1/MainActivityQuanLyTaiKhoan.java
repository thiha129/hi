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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivityQuanLyTaiKhoan extends AppCompatActivity {
    public static ArrayList<Nguoidung> nguoidungArrayList;
    public static NguoidungAdapter adapter;
    public static DatabaseLogin databaseLogin;
    ListView listView;
    TextView showTaikhoan;
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
        setContentView(R.layout.activity_main_quan_ly_tai_khoan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = findViewById(R.id.listnguoidung);
        nguoidungArrayList = new ArrayList<>();
        databaseLogin = new DatabaseLogin(this, "mail.sqlite", null, 1);
        MainActivityQuanLyTaiKhoan.databaseLogin.UpData("CREATE TABLE IF NOT EXISTS TaiKhoan6 (Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten COLLATE NOCASE, Pass VARCHAR(200), Hovaten COLLATE NOCASE, SoDienThoai VARCHAR(11),Ngay VARCHAR(20), diachi VARCHAR(200))");
        abc02();
        Xoa();
    }

    private void Xoa() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                indexItem = i;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityQuanLyTaiKhoan.this);
                builder.setTitle(R.string.app_name);
                builder.setMessage("Bạn có muốn xóa không?");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        databaseLogin.UpData("delete from TaiKhoan6 where Id = " + nguoidungArrayList.get(indexItem).getId() + "");
                        nguoidungArrayList.clear();
                        abc02();
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

    private void abc02() {
        showTaikhoan = findViewById(R.id.SoluongTaiKhoan);
        adapter = new NguoidungAdapter(MainActivityQuanLyTaiKhoan.this, R.layout.list_item_nguoidung, nguoidungArrayList);
        Cursor cursor = databaseLogin.GetData("SELECT * FROM TaiKhoan6");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String HovaTen = cursor.getString(1);
                String MatKhau = cursor.getString(2);
                String TendangNhap  = cursor.getString(3);
                String SoDienThoai = cursor.getString(4);
                String Ngaytao = cursor.getString(5);
                String DiaChi = cursor.getString(6);
                nguoidungArrayList.add(new Nguoidung(id,TendangNhap ,MatKhau  ,HovaTen , SoDienThoai, Ngaytao, DiaChi));
            }
            listView.setAdapter(adapter);
        } else {
            Toast.makeText(MainActivityQuanLyTaiKhoan.this, "NOT OK", Toast.LENGTH_SHORT).show();
        }
        showTaikhoan.setText("Số tài khoản hiện đã đăng kí:" + adapter.getCount());

    }
}