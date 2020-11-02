package com.example.test1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityUser extends AppCompatActivity {
    SharedPreferences mysharedPreferences;
    TextView user_changePass, user_Infomation, user_giohang, user_notification, user_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        AnhXa();
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
    }

    private void AnhXa() {
        user_changePass = findViewById(R.id.User_DoiMatKhau);
        user_Infomation = findViewById(R.id.User_Info);
        user_giohang = findViewById(R.id.Usser_GioHang);
        user_notification = findViewById(R.id.User_Noti);
        user_exit = findViewById(R.id.User_Exit);

    }
}