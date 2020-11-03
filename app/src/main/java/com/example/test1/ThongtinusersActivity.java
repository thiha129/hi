package com.example.test1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ThongtinusersActivity extends AppCompatActivity {
    public static DatabaseLogin databaseLogin;
    TextView Ten_ngdung,Sdt_ngdung,diachi_ngdung,ngaytao_ngdung,login_ngdung;
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
        setContentView(R.layout.activity_thongtinusers);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseLogin = new DatabaseLogin(ThongtinusersActivity.this, "mail.sqlite", null, 1);
        MainActivityUser.databaseLogin.UpData("CREATE TABLE IF NOT EXISTS TaiKhoan6 (Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten COLLATE NOCASE, Pass VARCHAR(200), Hovaten COLLATE NOCASE, SoDienThoai VARCHAR(11),Ngay VARCHAR(20), diachi VARCHAR(200))");
        AnhXa();
    }

    private void AnhXa() {
        Ten_ngdung = findViewById(R.id.title_view);
        Sdt_ngdung = findViewById(R.id.User_SDT);
        diachi_ngdung = findViewById(R.id.User_DIACHI);
        ngaytao_ngdung = findViewById(R.id.Usser_NGAYTAO);
        login_ngdung = findViewById(R.id.User_TENDANGNHAP);
        Intent i = getIntent();
        int Id = i.getIntExtra("id_User", 1);
        Cursor cursor = databaseLogin.GetData("SELECT * FROM TaiKhoan6 where Id = " + Id + "");
        while (cursor.moveToNext()){
            String Name_ngdung = cursor.getString(3);
            Ten_ngdung.setText(Name_ngdung);
            String Number_ngdung = cursor.getString(4);
            Sdt_ngdung.setText(Number_ngdung);
            String address_ngdung = cursor.getString(6);
            diachi_ngdung.setText(address_ngdung);
            String date_ngdung = cursor.getString(5);
            ngaytao_ngdung.setText(date_ngdung);
            String tendangnhap = cursor.getString(1);
            login_ngdung.setText(tendangnhap);
        }
    }
}