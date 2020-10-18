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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class MainActivityThongTinSanPham extends AppCompatActivity {
    TextView name, nd, race, number, test;
    ImageView imageView1;
    public static DatabaseHelper databaseHelper;
    Button GioHang;
    Intent intent   ;
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
        setContentView(R.layout.activity_main_thong_tin_san_pham);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseHelper = new DatabaseHelper(MainActivityThongTinSanPham.this, "giaydep", null, 1);
        databaseHelper.UpData("CREATE TABLE IF NOT EXISTS Sanpham1(Id INTEGER PRIMARY KEY AUTOINCREMENT,Ten VarChar(150), Gia VarChar(150), SoLuong VarChar(150), LinkAnh VarChar(150),Chitiet VarChar(150))");
        GioHang= findViewById(R.id.chuyengiohang);

        GioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = getIntent();
                int Id = i.getIntExtra("id", 1);
                Cursor cursor = databaseHelper.GetData("SELECT * FROM Sanpham1 where Id = " + Id + "");
                while (cursor.moveToNext()) {
                    cursor.getString(1);
                    cursor.getString(2);
                    databaseHelper.UpData("Insert into GioHang values(null,'"+ cursor.getString(1)+"','"+ cursor.getString(2)+"')");

                }
                      intent = new Intent(MainActivityThongTinSanPham.this, MainActivityGioHang.class);
                      startActivity(intent);
            }
        });
        AnhXa();
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
            imageView1.setImageBitmap(bitmap);
            super.onPostExecute(bitmap);
        }
    }

    private void AnhXa() {
        name = findViewById(R.id.tenSanPham);
        race = findViewById(R.id.GiaSanPham);
        number = findViewById(R.id.SoLuongSanPham);
        nd = findViewById(R.id.ChiTietSanPham);
        imageView1 = findViewById(R.id.imageView3);
       
        Intent i = getIntent();
        int Id = i.getIntExtra("id", 1);
        Cursor cursor = databaseHelper.GetData("SELECT * FROM Sanpham1 where Id = " + Id + "");
        while (cursor.moveToNext()) {
            if (cursor.getInt(0) == Id) {
                String Name = cursor.getString(1);
                name.setText(Name);
                String Race = cursor.getString(2);
                race.setText(Race);
                String Number = cursor.getString(3);
                number.setText(Number);
                String link = cursor.getString(4);
//                test.setText(link);

                MainActivityThongTinSanPham.LoadImage loadImages = new MainActivityThongTinSanPham.LoadImage(imageView1);
                loadImages.execute(link);
                String ND = cursor.getString(5);
                nd.setText(ND);

            }
        }

    }



}