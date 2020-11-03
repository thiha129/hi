package com.example.test1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityThongTin extends AppCompatActivity {
    SharedPreferences mysharedPreferences;
    Button btn, Exit,them, quanlytaikhoan, order, change, noti;
    Intent intent;
    TextView ten, sdt;
    public static DatabaseLogin databaseLogin;

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
              intent = new Intent(MainActivityThongTin.this, MainActivity.class);
              startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thong_tin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseLogin = new DatabaseLogin(MainActivityThongTin.this, "mail.sqlite", null, 1);
        MainActivityThongTin.databaseLogin.UpData("CREATE TABLE IF NOT EXISTS TaiKhoan6 (Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten COLLATE NOCASE, Pass VARCHAR(200), Hovaten COLLATE NOCASE, SoDienThoai VARCHAR(11),Ngay VARCHAR(20), diachi VARCHAR(200))");
        mysharedPreferences = getSharedPreferences("mylogin", MODE_PRIVATE);
        SharedPreferences.Editor myeditor = mysharedPreferences.edit();

        ten = findViewById(R.id.tennguoidung);
        Cursor cursor1 = databaseLogin.GetData("SELECT * FROM TaiKhoan6 WHERE Ten LIKE '%"+mysharedPreferences.getString("username","")+"%' ");
        while (cursor1.moveToNext()){
            String tenmoi = cursor1.getString(3);
            ten.setText(tenmoi);
        }
        btn = findViewById(R.id.btnDoiMatkhau);
         quanlytaikhoan = findViewById(R.id.btndanhsachnguoidung);
         quanlytaikhoan.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(MainActivityThongTin.this, MainActivityQuanLyTaiKhoan.class);
                 startActivity(intent);
             }
         });
         noti = findViewById(R.id.btnNoti);
         noti.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                   intent = new Intent(MainActivityThongTin.this, MainActivity4.class);
                   startActivity(intent);
             }
         });
         change=findViewById(R.id.suaXOa);
         change.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(MainActivityThongTin.this, MainActivityQuanLyHoaDon.class);
                 startActivity(intent);
             }
         });
        them = findViewById(R.id.btnThem);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivityThongTin.this, MainActivitySuaXoaSanPham.class);
                startActivity(intent);
            }
        });
         order = findViewById(R.id.tam);
         order.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 intent = new Intent(MainActivityThongTin.this, MainActivityThongKe.class);
                 startActivity(intent);

             }
         });
         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 intent = new Intent(MainActivityThongTin.this, MainActivityDoiMatKhau.class);
                 startActivity(intent);
             }
         });

        Exit = findViewById(R.id.btnExit);
//        SharedPreferences.Editor myeditor = mysharedPreferences.edit();
//        myeditor.remove("username");
//        myeditor.commit();
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mysharedPreferences = getSharedPreferences("mylogin", MODE_PRIVATE);
                final LoadingDialog loadingDialog = new LoadingDialog(MainActivityThongTin.this);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivityThongTin.this);
                alertDialog.setTitle("Thông báo");
                alertDialog.setIcon(R.drawable.icons8_error_xanh);
                alertDialog.setMessage("Bạn có muốn thoát");
                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences.Editor myeditor = mysharedPreferences.edit();
                        myeditor.remove("username");
                        myeditor.commit();
                        loadingDialog.startLoadingDialog();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialog.dismissDialog();
                                startActivity(new Intent(MainActivityThongTin.this, MainActivityDangNhap.class));
                                Toast.makeText(MainActivityThongTin.this, "Successfully logged out !", Toast.LENGTH_SHORT).show();

                            }
                        },2000);
                    }
                });

                alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.show();
            }
        });
    }
}