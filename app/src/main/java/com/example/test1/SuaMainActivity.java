package com.example.test1;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
        databaseHelper.UpData("CREATE TABLE IF NOT EXISTS Sanpham2(Id INTEGER PRIMARY KEY AUTOINCREMENT,Ten VarChar(150), Gia VarChar(150), SoLuong VarChar(150), LinkAnh Text ,Chitiet VarChar(150), size VarChar(150))");
        AnhXa();
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
                databaseHelper.UpData("UPDATE Sanpham2 SET Ten = '"+TenMoi+"', Gia = '"+GiaMoi+"', SoLuong = '"+SoluongMoi+"', LinkAnh = '"+LinkMoi+"',Chitiet = '"+ChiTietMoi+"',size ='"+SizeMoi+"' WHERE Id = '"+Id+"'");
                intent = new Intent(SuaMainActivity.this, MainActivitySuaXoaSanPham.class);
                startActivity(intent);
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
        int Id = i.getIntExtra("idSp", 1);
        Cursor cursor = databaseHelper.GetData("SELECT * FROM Sanpham2 where Id = " + Id + "");
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