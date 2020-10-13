package com.example.test1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivityDangNhap extends AppCompatActivity {
    public static DatabaseLogin databaseLogin;
    Button btnLogin;
    TextView txtDangKy;
    Intent intent;
    EditText edtusername;
    EditText edtpassword;
    public static SharedPreferences mysharedPreferences;
    boolean Ktra1, Ktra2;
    TextInputLayout layoutUser;
    TextInputLayout layoutPass;

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
        setContentView(R.layout.activity_main_dang_nhap);
        databaseLogin = new DatabaseLogin(this, "mail.sqlite", null, 1);
        databaseLogin.UpData("CREATE TABLE IF NOT EXISTS TaiKhoan (Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(200), Pass VARCHAR(200),SoDienThoai VARCHAR(11))");

        AnhXa();
        batloi();

        txtDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivityDangNhap.this, MainActivityDangKy.class);
                startActivity(intent);
            }
        });
        mysharedPreferences = getSharedPreferences("mylogin", MODE_PRIVATE);
        final CheckBox checkBox = findViewById(R.id.checkBox);
        String user1 = mysharedPreferences.getString("username", "");
        String pwd1 = mysharedPreferences.getString("password", "");
        boolean abc = mysharedPreferences.getBoolean("checked", false);
        String islogin = mysharedPreferences.getString("username", "");
//        ----------------------------------------------------------------------------------------
        if (islogin != "") {
            Intent i = new Intent(MainActivityDangNhap.this, MainActivityThongTin.class);
            startActivity(i);
        }
        //        ----------------------------------------------------------------------------------------

//        if (abc == true) {
//            edtusername.setText(user1);
//            edtpassword.setText(pwd1);
//            checkBox.setChecked(true);
//        } else {
//            edtusername.setText("");
//            edtpassword.setText("");
//            checkBox.setChecked(false);
//        }
        btnLogin = findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Ktra1 = Ktra2) {
                    if (edtusername.equals("") && edtpassword.equals("")) {
                        Toast.makeText(MainActivityDangNhap.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                    } else {
                        CheckBox checkBox;
                        String user = edtusername.getText().toString().trim();
                        String pwd = edtpassword.getText().toString().trim();
                        Boolean res3 = databaseLogin.check(user, pwd);
                        checkBox = findViewById(R.id.checkBox);
                        boolean checkBox1 = checkBox.isChecked();
                        if (user.equals("") || pwd.equals("")) {
                            Toast.makeText(MainActivityDangNhap.this, "Không được để trống!", Toast.LENGTH_SHORT).show();
                        } else {
                            if (res3 == true) {
                                if (user.length() > 0 && pwd.length() > 0) {
                                    if (checkBox.isChecked()) {
                                        SharedPreferences.Editor editor = mysharedPreferences.edit();
                                        editor.putString("username", user);
                                        editor.putString("password", pwd);
                                        editor.putBoolean("checked", true);
                                        editor.commit();
//                                        loadingDialog.startLoadingDialog();
//                                        Handler handler = new Handler();
//                                        handler.postDelayed(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                loadingDialog.dismissDialog();
//
//                                            }
//                                        }, 2000);
                                        Intent danhnhap1 = new Intent(MainActivityDangNhap.this, MainActivityThongTin.class);
                                        startActivity(danhnhap1);
                                        Toast.makeText(MainActivityDangNhap.this, "Logged in successfully !", Toast.LENGTH_SHORT).show();
                                    } else {
                                        SharedPreferences.Editor editor = mysharedPreferences.edit();
                                        editor.remove("username");
                                        editor.remove("password");
                                        editor.remove("checked");
                                        editor.commit();
//                                        loadingDialog.startLoadingDialog();
//                                        Handler handler = new Handler();
//                                        handler.postDelayed(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                loadingDialog.dismissDialog();
//
//                                            }
//                                        }, 2000);
                                        Intent danhnhap1 = new Intent(MainActivityDangNhap.this, MainActivityThongTin.class);
                                        startActivity(danhnhap1);
                                        Toast.makeText(MainActivityDangNhap.this, "Logged in successfully !", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(MainActivityDangNhap.this, "Login failed !", Toast.LENGTH_SHORT).show();

                                }
                            } else {
                                Toast.makeText(MainActivityDangNhap.this, "Login Error", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                } else {
                    Toast.makeText(MainActivityDangNhap.this, "Chưa đúng thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void AnhXa() {
        layoutPass = findViewById(R.id.wrapPass);
        layoutUser = findViewById(R.id.wrapUser);
        edtusername = findViewById(R.id.edtemail);
        edtpassword = findViewById(R.id.edtpassword);
        btnLogin = findViewById(R.id.btnlogin);
        txtDangKy = findViewById(R.id.dangky);
    }

    private void batloi() {
        AnhXa();
        edtusername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    layoutUser.setError("Không được để trống");
                } else {
                    if (charSequence.length() < 5 || charSequence.length() > 30) {
                        layoutUser.setError("Tên đăng nhập từ 5 đến 30 kí tự  !");
                    } else {
                        layoutUser.setError(null);
                        Ktra2 = true;
                    }


                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    layoutPass.setError("Không được để trống");
                } else {
                    if (charSequence.length() < 8 || charSequence.length() > 30) {
                        layoutPass.setError("Mật khẩu từ 8 đến 30 kí tự");
                    } else {
                        layoutPass.setError(null);
                        Ktra1 = true;
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


}