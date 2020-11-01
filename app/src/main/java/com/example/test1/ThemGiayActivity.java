package com.example.test1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ThemGiayActivity extends AppCompatActivity {
    AutoCompleteTextView size;
    private static final String[] PRODUCTS = new String[]
            {
                    "40", "41", "42", "43", "44"
            };
    public static DatabaseHelper databaseHelper;

    public static ArrayList<Giay> arrayDoVat;
    public static GiayAchapter adapter;
    public Button btnthem2, btnhuy2;
    EditText Ten, Gia, SoLuong, LinkAnh, Chitiet;
    EditText etURL;
    Button btnClear, btnSubmit;
    ImageView ivResult;

    Intent intent;

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
        setContentView(R.layout.activity_them_giay);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AnhXa();
        databaseHelper = new DatabaseHelper(ThemGiayActivity.this, "giaydep", null, 1);
        databaseHelper.UpData("CREATE TABLE IF NOT EXISTS Sanpham3(Id INTEGER PRIMARY KEY AUTOINCREMENT,Ten VarChar(150), Gia VarChar(150), SoLuong VarChar(150), LinkAnh Text ,Chitiet VarChar(150), size VarChar(150))");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, PRODUCTS);

        size.setAdapter(adapter);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlLink = etURL.getText().toString();
                if (urlLink.isEmpty()) {
                    Toast.makeText(ThemGiayActivity.this, "Please enter url", Toast.LENGTH_SHORT).show();
                } else {
                    LoadImage loadImages = new LoadImage(ivResult);
                    loadImages.execute(urlLink);
                }


            }
        });
//--------------------------------------------------------------------------------------------------------------------------
        btnthem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ten.getText().toString();
                Gia.getText().toString();
                SoLuong.getText().toString();
                LinkAnh.getText().toString();
                Chitiet.getText().toString();
                size.getText().toString();
                if (etURL.length() > 0 && Gia.length() > 0 && Ten.length() > 0 && SoLuong.length() > 0 && Chitiet.length() > 0 && size.length() > 0) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(ThemGiayActivity.this);
                    builder.setTitle("Thêm dữ liệu");
                    builder.setMessage("Bạn có thực sự muốn gửi không?");
                    builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(ThemGiayActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            databaseHelper.UpData("Insert into Sanpham3 Values(null,'" + Ten.getText().toString() + "','" + Gia.getText().toString() + "','" + SoLuong.getText().toString() + "', '" + LinkAnh.getText().toString() + "', '" + Chitiet.getText().toString() + "','" + size.getText().toString() + "')");
                            etURL.setText("");
                            ivResult.setImageBitmap(null);
                            Ten.setText("");
                            Gia.setText("");
                            SoLuong.setText("");
                            LinkAnh.setText("");
                            Chitiet.setText("");
                            size.setText("");
//                        intent = new Intent(ThemGiayActivity.this, MainActivitySuaXoaSanPham.class);
//                        startActivity(intent);
                        }

                    });
                    builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(ThemGiayActivity.this, "Hủy thành công", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.create().show();
                } else {
                    btnthem2.setEnabled(true);
                    Toast.makeText(ThemGiayActivity.this, "Vui lòng bạn điền đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnhuy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ThemGiayActivity.this, MainActivitySuaXoaSanPham.class);
                startActivity(intent);
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etURL.setText("");
                ivResult.setImageBitmap(null);
                Ten.setText("");
                Gia.setText("");
            }
        });

        Batloi();
    }

    private void AnhXa() {
        btnClear = findViewById(R.id.btn_clear);
        btnhuy2 = findViewById(R.id.btnHuysp);
        btnSubmit = findViewById(R.id.btn_submit);
        etURL = findViewById(R.id.et_ulr);
        ivResult = findViewById(R.id.iv_result);
        size = findViewById(R.id.sizesp);
        Ten = findViewById(R.id.tensp);
        Gia = findViewById(R.id.giasp);
        SoLuong = findViewById(R.id.soluongsp);
        LinkAnh = findViewById(R.id.et_ulr);
        Chitiet = findViewById(R.id.chitietsp);
        btnthem2 = findViewById(R.id.btnthemsp);
    }

    private void Batloi() {
        etURL.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    etURL.setError("Vui lòng không được đẻ trống !");
                } else {
                    etURL.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------
        Gia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    Gia.setError("Vui lòng không được đẻ trống !");
                } else {
                    Gia.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------
        Ten.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    Ten.setError("Vui lòng không được đẻ trống !");
                } else {
                    Ten.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------
        SoLuong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    SoLuong.setError("Vui lòng không được đẻ trống !");
                } else {
                    SoLuong.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------
        Chitiet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    Chitiet.setError("Vui lòng không được đẻ trống !");
                } else {
                    Chitiet.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------
        size.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    size.setError("Vui lòng không được đẻ trống !");
                } else {
                    size.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    private class LoadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public LoadImage(ImageView ivResult) {
            this.imageView = ivResult;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String urlLink = strings[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new java.net.URL(urlLink).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ivResult.setImageBitmap(bitmap);
            super.onPostExecute(bitmap);
        }
    }

}