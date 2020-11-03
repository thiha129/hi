package com.example.test1;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivityThongBaoUser extends AppCompatActivity {
    public static DatabaseHelper databaseHelper;
    public static ArrayList<noti> arrayNoti;
    public static NotiAdapter adapter;
    ListView listView ;
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
        setContentView(R.layout.activity_main_thong_bao_user);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseHelper = new DatabaseHelper(MainActivityThongBaoUser.this, "giaydep", null, 1);
        databaseHelper.UpData("CREATE TABLE IF NOT EXISTS Noti(Id INTEGER PRIMARY KEY AUTOINCREMENT,TieuDe Text,NoiDung Text )");
        listView = findViewById(R.id.List_thongbaoUser);
        arrayNoti = new ArrayList<>();
        adapter = new NotiAdapter(MainActivityThongBaoUser.this, arrayNoti, R.layout.list_item_noti);
        listView.setAdapter(adapter);
        Loading();
    }
    private void Loading() {
        adapter = new NotiAdapter(MainActivityThongBaoUser.this, arrayNoti, R.layout.list_item_noti);
        Cursor cursor = databaseHelper.GetData("SELECT * FROM Noti");
        if (cursor != null) {
            while (cursor.moveToNext()) {
//                int id = cursor.getInt(0);
                String Ten = cursor.getString(1);
                String Nd = cursor.getString(2);
                arrayNoti.add(new noti(null, Ten, Nd));
            }
            adapter.notifyDataSetChanged();
        } else {
//            Toast.makeText(MainActivitySuaXoaSanPham.this, "NOT OK", Toast.LENGTH_SHORT).show();
        }
    }
}