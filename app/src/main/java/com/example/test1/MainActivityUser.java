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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class MainActivityUser extends AppCompatActivity {
    SharedPreferences mysharedPreferences;
    public static DatabaseLogin databaseLogin;
    TextView user_changePass, user_Infomation, user_giohang, user_notification, user_exit, name_ủser;
    Intent intent;
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                intent = new Intent(MainActivityUser.this, MainActivity.class);
                startActivity(intent);
                Animatoo.animateSlideRight(MainActivityUser.this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseLogin = new DatabaseLogin(MainActivityUser.this, "mail.sqlite", null, 1);
        MainActivityUser.databaseLogin.UpData("CREATE TABLE IF NOT EXISTS TaiKhoan6 (Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten COLLATE NOCASE, Pass VARCHAR(200), Hovaten COLLATE NOCASE, SoDienThoai VARCHAR(11),Ngay VARCHAR(20), diachi VARCHAR(200))");
        mysharedPreferences = getSharedPreferences("mylogin", MODE_PRIVATE);
//        Toast.makeText(this, ""+mysharedPreferences.getString("username",""), Toast.LENGTH_SHORT).show();
        AnhXa();
//        name_ủser.setText(""+mysharedPreferences.getString("username",""));
//        Intent i = getIntent();
//        int IdMail = i.getIntExtra("id", 1);
////        name_ủser.setText(""+IdMail);
        Cursor cursor1 = databaseLogin.GetData("SELECT * FROM TaiKhoan6 WHERE Ten LIKE '%"+mysharedPreferences.getString("username","")+"%' ");
        while (cursor1.moveToNext()){
            String tenmoi = cursor1.getString(3);
            name_ủser.setText(tenmoi);
            int id = cursor1.getInt(0);
        }


        ChuyenTrang();
        user_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mysharedPreferences = getSharedPreferences("mylogin", MODE_PRIVATE);
                final LoadingDialog loadingDialog = new LoadingDialog(MainActivityUser.this);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivityUser.this);
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
                                startActivity(new Intent(MainActivityUser.this, MainActivityDangNhap.class));
                                Toast.makeText(MainActivityUser.this, "Successfully logged out !", Toast.LENGTH_SHORT).show();

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

    private void ChuyenTrang() {
        user_changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityUser.this, MainActivityDoiMatKhau.class));
            }
        });
        user_giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityUser.this, MainActivityGioHang.class));
            }
        });
        user_Infomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 intent = new Intent(MainActivityUser.this, ThongtinusersActivity.class);
                Cursor cursor1 = databaseLogin.GetData("SELECT * FROM TaiKhoan6 WHERE Ten LIKE '%"+mysharedPreferences.getString("username","")+"%' ");
                while (cursor1.moveToNext()){
                    String tenmoi = cursor1.getString(3);
                    name_ủser.setText(tenmoi);
                     cursor1.getInt(0);
                    intent.putExtra("id_User",cursor1.getInt(0));

                }
                startActivity(intent);
            }
        });
        user_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityUser.this, MainActivityThongBaoUser.class));

            }
        });
    }

    private void AnhXa() {
        user_changePass = findViewById(R.id.User_DoiMatKhau);
        user_Infomation = findViewById(R.id.User_Info);
        user_giohang = findViewById(R.id.Usser_GioHang);
        user_notification = findViewById(R.id.User_Noti);
        user_exit = findViewById(R.id.User_Exit);
        name_ủser = findViewById(R.id.title_view);
    }
}