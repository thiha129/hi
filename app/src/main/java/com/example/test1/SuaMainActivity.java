package com.example.test1;

import android.content.Intent;
import android.database.Cursor;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class SuaMainActivity extends AppCompatActivity {
    AutoCompleteTextView size;
    private static final String[] PRODUCTS = new String[]
            {
                    "40", "41", "42", "43", "44"
            };
    public static DatabaseHelper databaseHelper;
    Intent intent;
    Button btntSua, btnhuy2;
    EditText Ten, Gia, SoLuong, LinkAnh, Chitiet;
    EditText etURL;
    Button btnClear, btnSubmit;
    ImageView ivResult;

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
        setContentView(R.layout.activity_sua_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseHelper = new DatabaseHelper(SuaMainActivity.this, "giaydep", null, 1);
        databaseHelper.UpData("CREATE TABLE IF NOT EXISTS Sanpham3(Id INTEGER PRIMARY KEY AUTOINCREMENT,Ten VarChar(150), Gia VarChar(150), SoLuong VarChar(150), LinkAnh Text ,Chitiet VarChar(150), size VarChar(150))");
        AnhXa();
        Batloi();
        btntSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = getIntent();
                int Id = i.getIntExtra("id", 1);
                String LinkMoi = etURL.getText().toString().trim();
                String TenMoi = Ten.getText().toString().trim();
                String GiaMoi = Gia.getText().toString().trim();
                String SoluongMoi = SoLuong.getText().toString().trim();
                String ChiTietMoi = Chitiet.getText().toString().trim();
                String SizeMoi =  size.getText().toString().trim();
                databaseHelper.UpData("UPDATE Sanpham3 SET Ten = '"+TenMoi+"', Gia = '"+GiaMoi+"', SoLuong = '"+SoluongMoi+"', LinkAnh = '"+LinkMoi+"',Chitiet = '"+ChiTietMoi+"',size ='"+SizeMoi+"' WHERE Id = '"+Id+"'");
                intent = new Intent(SuaMainActivity.this, MainActivitySuaXoaSanPham.class);
                startActivity(intent);
            }
        });
        btnhuy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SuaMainActivity.this, MainActivitySuaXoaSanPham.class));
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, PRODUCTS);

        size.setAdapter(adapter);
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
        ImageView ivResult;

        public LoadImage(ImageView ivResult) {
            this.ivResult = ivResult;
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

    private void AnhXa() {
        Ten = findViewById(R.id.tensp);
        Gia = findViewById(R.id.giasp);
        SoLuong = findViewById(R.id.soluongsp);
        LinkAnh = findViewById(R.id.et_ulr);
        Chitiet = findViewById(R.id.chitietsp);
        btnClear = findViewById(R.id.btn_clear);
        btnhuy2 = findViewById(R.id.btnHuysp);
        btnSubmit = findViewById(R.id.btn_submit);
        etURL = findViewById(R.id.et_ulr);
        ivResult = findViewById(R.id.iv_result);
        size = findViewById(R.id.sizesp);
        btntSua = findViewById(R.id.btnSuasp);
        Intent i = getIntent();
        int Id = i.getIntExtra("id", 1);
        Cursor cursor = databaseHelper.GetData("SELECT * FROM Sanpham3 where Id = " + Id + "");
        while (cursor.moveToNext()) {
            if (cursor.getInt(0) == Id) {
                String Name = cursor.getString(1);
                Ten.setText(Name);
                String Race = cursor.getString(2);
                Gia.setText(Race);
                String Number = cursor.getString(3);
                SoLuong.setText(Number);
                String link = cursor.getString(4);
//                test.setText(link);
                etURL.setText(link);
                SuaMainActivity.LoadImage loadImages = new SuaMainActivity.LoadImage(ivResult);
                loadImages.execute(link);
                String ND = cursor.getString(5);
                Chitiet.setText(ND);
                String Size = cursor.getString(6);
                size.setText(Size);
            }
        }
    }
}