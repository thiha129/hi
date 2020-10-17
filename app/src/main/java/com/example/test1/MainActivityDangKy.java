package com.example.test1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivityDangKy extends AppCompatActivity {
    public static DatabaseLogin databaseLogin;
    TextView txtDangNhap,txtNgay;
    Button btnRegister;
    Intent intent;
    EditText edthoten;
    EditText edtSDT;
    EditText edtusername;
    EditText edtpassword;
    EditText edtcnfpassword;
    TextInputLayout layoutPass_1, layoutPass_2;
    private int Tongtaikhoang;
    private int tong=Tongtaikhoang;

    boolean Ktra1 = false, Ktra2 = false, Ktra3 = false, Ktra4 = false;

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
        setContentView(R.layout.activity_main_dang_ky);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseLogin = new DatabaseLogin(this, "mail.sqlite", null, 1);
        MainActivityDangKy.databaseLogin.UpData("CREATE TABLE IF NOT EXISTS TaiKhoan2 (Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(200), Pass VARCHAR(200), Hovaten VARCHAR(200), SoDienThoai VARCHAR(11),Ngay VARCHAR(20))");
        AnhXa();
        batloi();
        txtNgay = findViewById(R.id.txtDate);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        final String NgayGui = simpleDateFormat.format(date);
        txtNgay.setText(NgayGui);
        txtDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivityDangKy.this, MainActivityDangNhap.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtusername.getText().toString().trim();
                String pwd = edtpassword.getText().toString().trim();
                String cnf_pwd = edtcnfpassword.getText().toString().trim();
                String hovaten = edthoten.getText().toString().trim();
                String sodienthoai = edtSDT.getText().toString().trim();
                String Ngay = txtNgay.getText().toString().trim();
                if (pwd.equals(cnf_pwd)) {
                    Boolean res = MainActivityDangNhap.databaseLogin.checkUser(user);
                    if (res == true) {
                        Toast.makeText(MainActivityDangKy.this, "Tài khoản này đã có!", Toast.LENGTH_SHORT).show();
                    } else {
                        long val = MainActivityDangNhap.databaseLogin.addUser(user, pwd, hovaten, sodienthoai,Ngay);
                        if (val > 0) {
                            Toast.makeText(MainActivityDangKy.this, "Đăng kí thành công!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivityDangKy.this, MainActivityDangNhap.class);
                            startActivity(intent);
//                            Animato.animateSpin(MainActivityDangKy.this);

                        } else {
                            Toast.makeText(MainActivityDangKy.this, "Đăng kí thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(MainActivityDangKy.this, "Nhập lại mật khẩu phải giống nhau!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void batloi() {
        edtSDT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    edtSDT.setError("Không được để trống");
                } else {
                    if (charSequence.length() == 10) {
                        edtSDT.setError(null);
                        Ktra1 = true;
                    } else {
                        edtSDT.setError("Bạn phải nhập đúng số điện thoại 10 số");
                        Ktra1 = false;
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edthoten.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    edthoten.setError("Không được để trống");
                } else {
                    if (charSequence.length() < 8 || charSequence.length() > 30) {
                        edthoten.setError("họ và tên từ 8 đến 30 kí tự");
                        Ktra2 = false;
                    } else {
                        edthoten.setError(null);
                        Ktra2 = true;
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        final String checkMail = "[a-zA-Z0-9.]+@[a-z]+\\.+[a-z]+";
        edtusername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    edtusername.setError("Không được để trống");
                } else {
                    if (charSequence.toString().matches(checkMail)) {

                        edtusername.setError(null);
                        Ktra3 = true;
                    } else {
                        edtusername.setError("Bạn phải nhập đúng cấu trúc email");
                        Ktra3 = false;
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
//        edtpassword.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (charSequence.length() == 0) {
//                    layoutPass_1.setError("Không được để trống");
//                } else {
//                    if (charSequence.length() < 8 || charSequence.length() > 30) {
//                        layoutPass_1.setError("Password từ 8 đến 30 kí tự");
//                        Ktra4 = false;
//                    } else {
//                        edtpassword.setError(null);
//                        Ktra4 = true;
//                    }
//
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                                      nghe t nói gì k
        //mic m bị gi r
//            }
//        });
    }

    private void AnhXa() {
        edtcnfpassword =findViewById(R.id.edtpass2);
        txtDangNhap = findViewById(R.id.login);
        edtSDT = findViewById(R.id.edtSDT);
        edthoten = findViewById(R.id.edthvt);
        edtusername = findViewById(R.id.edtmail);
        edtpassword = findViewById(R.id.edtPass_1);
        btnRegister = findViewById(R.id.btndangki);
        layoutPass_1 = findViewById(R.id.WrapPass_1);
        layoutPass_2 = findViewById(R.id.WrapPass_2);
    }
}