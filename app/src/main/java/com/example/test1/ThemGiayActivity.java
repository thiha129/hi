package com.example.test1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test1.ui.home.HomeFragment;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ThemGiayActivity extends AppCompatActivity {
<<<<<<< HEAD
    private DatabaseHelper databaseHelper;
    private HomeViewModel homeViewModel;
=======
    public DatabaseHelper databaseHelper;
>>>>>>> cd84c755a229aa855a207ebbacd4cf2a06193265
    public static ArrayList<Giay> arrayDoVat;
    public static GiayAdapter adapter;
    Button btnthem2, btnhuy2;
    EditText Ten, Gia, SoLuong;
    EditText etURL;
    Button btnClear, btnSubmit;
    ImageView ivResult;
<<<<<<< HEAD
=======

>>>>>>> cd84c755a229aa855a207ebbacd4cf2a06193265
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_giay);
<<<<<<< HEAD
        btnClear=findViewById(R.id.btn_clear);
        btnSubmit=findViewById(R.id.btn_submit);
        etURL=findViewById(R.id.et_ulr);
        ivResult=findViewById(R.id.iv_result);
=======

        btnClear = findViewById(R.id.btn_clear);
        btnSubmit = findViewById(R.id.btn_submit);
        etURL = findViewById(R.id.et_ulr);
        ivResult = findViewById(R.id.iv_result);
        Ten = findViewById(R.id.tensp);
        Gia = findViewById(R.id.giasp);
        SoLuong = findViewById(R.id.soluongsp);
>>>>>>> cd84c755a229aa855a207ebbacd4cf2a06193265
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
<<<<<<< HEAD
                Ten = findViewById(R.id.tensp);
                Gia = findViewById(R.id.giasp);
                SoLuong =findViewById(R.id.soluongsp);
                Ten.getText().toString();
                Gia.getText().toString();
                SoLuong.getText().toString();
                Toast.makeText(ThemGiayActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                databaseHelper.UpData("Insert into Sanpham Values(null,'"+Ten.getText().toString()+"','" + Gia.getText().toString() + "','" + SoLuong.getText().toString() + "')");
                adapter.notifyDataSetChanged();
                Toast.makeText(ThemGiayActivity.this, "OK! "+Ten.getText().toString(), Toast.LENGTH_SHORT).show();
=======
                Toast.makeText(ThemGiayActivity.this, ""+ Ten.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        btnhuy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThemGiayActivity.this, MainActivity.class));
>>>>>>> cd84c755a229aa855a207ebbacd4cf2a06193265
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
//    private void abc02() {
//        adapter = new GiayAdapter(getActivity(), R.layout.list_item_abc, arrayDoVat);
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
//
//    }
}