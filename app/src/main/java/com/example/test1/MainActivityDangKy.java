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

public class MainActivityDangKy extends AppCompatActivity {
    public static DatabaseLogin databaseLogin;
    TextView txtDangNhap;
    Button btnRegister;
    Intent intent;
    EditText edthoten;
    EditText edtSDT;
    EditText edtusername;
    EditText edtpassword_1;
    EditText edtcnfpassword;
    TextInputLayout layoutPass_1, layoutPass_2;

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
        databaseLogin.UpData("CREATE TABLE IF NOT EXISTS TaiKhoan (Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(200), Pass VARCHAR(200),SoDienThoai VARCHAR(11))");
        AnhXa();
        batloi();
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
                String pwd = edtpassword_1.getText().toString().trim();
                String cnf_pwd = edtcnfpassword.getText().toString().trim();

                String sodienthoai = edtSDT.getText().toString().trim();
                if (pwd.equals(cnf_pwd)) {
                    Boolean res = MainActivityDangKy.databaseLogin.checkUser(user);
                    if (res == true) {
                        Toast.makeText(MainActivityDangKy.this, "Tài khoản này đã có!", Toast.LENGTH_SHORT).show();
                    } else {
                        long val = MainActivityDangKy.databaseLogin.addUser(user, pwd, sodienthoai);
                        if (val > 0 && edtusername.length() != 0 && edtpassword_1.length() != 0 && edtcnfpassword.length() != 0 && edtSDT.length() != 0) {
                            Toast.makeText(MainActivityDangKy.this, "Đăng kí thành công!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivityDangKy.this, MainActivityDangNhap.class);
                            startActivity(intent);
//                            Animatoo.animateSpin(dangki.this);

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
        edtusername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    edtusername.setError("Không được để trống");
                } else {
                    if (charSequence.length() < 8 || charSequence.length() > 30) {
                        edtusername.setError("họ và tên từ 8 đến 30 kí tự");
                        Ktra2 = false;
                    } else {
                        edtusername.setError(null);
                        Ktra2 = true;
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtpassword_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    layoutPass_1.setError("Không được để trống");
                } else {
                    if (charSequence.length() < 8 || charSequence.length() > 30) {
                        layoutPass_1.setError("Password từ 8 đến 30 kí tự");
                        Ktra4 = false;
                    } else {
                        layoutPass_1.setError(null);
                        Ktra4 = true;
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtcnfpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    layoutPass_2.setError("Không được để trống");
                } else {
                    if (charSequence.equals(edtpassword_1)) {
                        layoutPass_2.setError("Nhập lại mật khẩu phải giống nhau!");
                        Ktra4 = false;
                    } else {
                        layoutPass_2.setError(null);
                        Ktra4 = true;
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void AnhXa() {
        txtDangNhap = findViewById(R.id.login);
        edtSDT = findViewById(R.id.edtSDT);
        edtusername = findViewById(R.id.edthvt);
        edtpassword_1 = findViewById(R.id.edtPass_1);
        edtcnfpassword = findViewById(R.id.edtpass2);
        btnRegister = findViewById(R.id.btndangki);
        layoutPass_1 = findViewById(R.id.WrapPass_1);
        layoutPass_2 = findViewById(R.id.WrapPass_2);
    }
}