package com.example.test1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
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
        databaseHelper = new DatabaseHelper(ThemGiayActivity.this, "giaydep", null, 1);
        databaseHelper.UpData("CREATE TABLE IF NOT EXISTS Sanpham2(Id INTEGER PRIMARY KEY AUTOINCREMENT,Ten VarChar(150), Gia VarChar(150), SoLuong VarChar(150), LinkAnh Text ,Chitiet VarChar(150), size VarChar(150))");
        btnClear = findViewById(R.id.btn_clear);
        btnhuy2 = findViewById(R.id.btnHuysp);
        btnSubmit = findViewById(R.id.btn_submit);
        etURL = findViewById(R.id.et_ulr);
        ivResult = findViewById(R.id.iv_result);
        size = findViewById(R.id.sizesp);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, PRODUCTS);

        size.setAdapter(adapter);
        sua();
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etURL.setText("");
                ivResult.setImageBitmap(null);

            }
        });
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
        btnthem2 = findViewById(R.id.btnthemsp);
        btnthem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ten = findViewById(R.id.tensp);
                Gia = findViewById(R.id.giasp);
                SoLuong = findViewById(R.id.soluongsp);
                LinkAnh = findViewById(R.id.et_ulr);
                Chitiet = findViewById(R.id.chitietsp);
                Ten.getText().toString();
                Gia.getText().toString();
                SoLuong.getText().toString();
                LinkAnh.getText().toString();
                Chitiet.getText().toString();
                size.getText().toString();
//                if (Ten.equals("") && Gia.equals("") && SoLuong.equals("") && LinkAnh.equals("") && Chitiet.equals("")) {
//                    Ten.setError("Không được để trống");
//                    Gia.setError("Không được để trống");
//                    SoLuong.setError("Không được để trống");
//                    LinkAnh.setError("Không được để trống");
//                    Chitiet.setError("Không được để trống");
//                } else if (Ten.equals("")) {
//                    Ten.setError("Không được để trống");
//                } else if (Gia.equals("")) {
//                    Gia.setError("Không được để trống");
//                } else if (SoLuong.equals("")) {
//                    SoLuong.setError("Không được để trống");
//                } else if (LinkAnh.equals("")) {
//                    LinkAnh.setError("Không được để trống");
//                } else if (Chitiet.equals("")) {
//                    Chitiet.setError("Không được để trống");
//                } else if (Ten.length() > 6 || Ten.length() < 30) {
//                    Ten.setError("Tên đăng nhập phải từ 6 đến 30 kí tự");
//                } else if (Gia.length() > 1 || Gia.length() < 5) {
//                    Gia.setError("Tên đăng nhập phải từ 1 đến 5 số");
//                } else if (SoLuong.length() > 0 || SoLuong.length() < 3) {
//                    SoLuong.setError("Tên đăng nhập phải từ 1 đến 2 số");
//                } else if (Chitiet.length() > 0 || Chitiet.length() < 150) {
//                    Chitiet.setError("Tên đăng nhập phải từ 1 đến 2 số");
//                } else {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ThemGiayActivity.this);
                builder.setTitle("Thêm dữ liệu");
                builder.setMessage("Bạn có thực sự muốn gửi không?");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ThemGiayActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        databaseHelper.UpData("Insert into Sanpham2 Values(null,'" + Ten.getText().toString() + "','" + Gia.getText().toString() + "','" + SoLuong.getText().toString() + "', '" + LinkAnh.getText().toString() + "', '" + Chitiet.getText().toString() + "','" + size.getText().toString() + "')");
                        Ten.setText("");
                        Gia.setText("");
                        SoLuong.setText("");
                        LinkAnh.setText("");
                        Chitiet.setText("");
                        size.setText("");
                        intent = new Intent(ThemGiayActivity.this, MainActivitySuaXoaSanPham.class);
                        startActivity(intent);
                    }

                });
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ThemGiayActivity.this, "Hủy thành công", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.create().show();
            }


//            }
        });
        btnhuy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ThemGiayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void sua() {

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
//    private void abc02() {
//        adapter = new GiayAdapter(ThemGiayActivity.this, R.layout.list_item_abc, arrayDoVat);
//        Cursor cursor = databaseHelper.GetData("SELECT * FROM Sanpham");
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                int id = cursor.getInt(0);
//                String TenGiay = cursor.getString(1);
//                String Gia = cursor.getString(2);
//                String Soluong = cursor.getString(3);
//                arrayDoVat.add(new Giay(id, TenGiay, Gia, Soluong));
//            }
//            gridView.setAdapter(adapter);
//        } else {
//            Toast.makeText(getActivity(), "NOT OK", Toast.LENGTH_SHORT).show();
//        }

//    }
}