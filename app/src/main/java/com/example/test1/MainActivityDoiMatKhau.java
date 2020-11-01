package com.example.test1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityDoiMatKhau extends AppCompatActivity {
    EditText pass1;
    EditText pass2;
    EditText pass3;
    Button btndoimatkhau;
    Button btnhuy;
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
        setContentView(R.layout.activity_main_doi_mat_khau);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Anhxa();
        final String[] edtpass1 = new String[1];// = pass1.getText().toString().trim();
        thuchien();
        btndoimatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtpass1[0] = pass1.getText().toString().trim();
//                Boolean res3 = homeActivity.databaseHelper.checkpass(edtpass1);
                if (pass1.equals("")) {
                    Toast.makeText(MainActivityDoiMatKhau.this, "Không được để trống!", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean res3 = MainActivityDangNhap.databaseLogin.checkpass(edtpass1[0]);
                    if (res3 == true) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityDoiMatKhau.this);
                        builder.setTitle("Sửa mật khẩu");
                        builder.setMessage("Bạn có thực sự muốn đổi mật khẩu không?");
//                    dạ k a e để trong ô textview dạ lấy cái ngày hiện tai a
                        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String edtpass2 = pass2.getText().toString().trim();
                                //ý làm j lấy cái id của data kiểu chi á a

//                                SharedPreferences.Editor editor = mysharedPreferences.edit();

                                String user = MainActivityDangNhap.mysharedPreferences.getString("username","");
//                                homeActivity.databaseHelper.checkUser(user,edtpass2);
                                MainActivityDangNhap.databaseLogin.UpData("Update TaiKhoan6 set Pass='" + edtpass2 + "' where Ten='" + user + "'");
                                Toast.makeText(MainActivityDoiMatKhau.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                MainActivityDangNhap.databaseLogin.UpData("Update TaiKhoan6 set Pass='" + edtpass2 + "' where Id='" + edtpass1[0] + "'");
                               
                                if (getFragmentManager().getBackStackEntryCount()>0){
                                    getFragmentManager().popBackStack();
                                    return;
                                }
                                MainActivityDoiMatKhau.super.onBackPressed();
                            }

                        });
                        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivityDoiMatKhau.this, "Hủy thành công", Toast.LENGTH_SHORT).show();

                            }
                        });
                        builder.create().show();
                    } else {
                        Toast.makeText(MainActivityDoiMatKhau.this, "Sai mật khẩu!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    private void thuchien() {

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityDoiMatKhau.this);
                builder.setTitle("Thoát");
                builder.setMessage("Bạn có thực sự muốn hủy không?");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (getFragmentManager().getBackStackEntryCount()>0){
                            getFragmentManager().popBackStack();
                            return;
                        }
                        MainActivityDoiMatKhau.super.onBackPressed();
                    }

                });
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivityDoiMatKhau.this, "Hủy thành công", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.create().show();
            }
        });
    }


    private void Anhxa() {
        pass1 = findViewById(R.id.edtpass1);
        pass2 = findViewById(R.id.edtpass);
        pass3 = findViewById(R.id.edtpass2);
        btndoimatkhau = findViewById(R.id.btndoimatkhau);
        btnhuy = findViewById(R.id.btnhuy);

    }
}