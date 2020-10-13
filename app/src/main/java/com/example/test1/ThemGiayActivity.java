package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.test1.ui.home.HomeFragment;
import com.example.test1.ui.home.HomeViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ThemGiayActivity extends AppCompatActivity {


    public static DatabaseHelper databaseHelper;

    public static ArrayList<Giay> arrayDoVat;
    public static GiayAdapter adapter;
    Button btnthem2, btnhuy2;
    EditText Ten, Gia,SoLuong, LinkAnh,Chitiet;
    EditText etURL;
    Button btnClear, btnSubmit;
    ImageView ivResult;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_giay);

        databaseHelper = new DatabaseHelper(ThemGiayActivity.this, "giaydep", null, 1);
        databaseHelper.UpData("CREATE TABLE IF NOT EXISTS Sanpham(Id INTEGER PRIMARY KEY AUTOINCREMENT,Ten VarChar(150), Gia VarChar(150), SoLuong VarChar(150))");


        btnClear=findViewById(R.id.btn_clear);
        btnSubmit=findViewById(R.id.btn_submit);
        etURL=findViewById(R.id.et_ulr);
        ivResult=findViewById(R.id.iv_result);
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
                    String urlLink=etURL.getText().toString();
                    if (urlLink.isEmpty()){
                        Toast.makeText(ThemGiayActivity.this, "Please enter url", Toast.LENGTH_SHORT).show();
                    }else {
                        LoadImage loadImages=new LoadImage(ivResult);
                        loadImages.execute(urlLink);
                    }


            }
        });
        btnthem2=findViewById(R.id.btnthemsp);
        btnthem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ten = findViewById(R.id.tensp);
                Gia = findViewById(R.id.giasp);
                SoLuong =findViewById(R.id.soluongsp);
                LinkAnh =findViewById(R.id.et_ulr);
                Chitiet =findViewById(R.id.chitietsp);
                Ten.getText().toString();
                Gia.getText().toString();
                SoLuong.getText().toString();
                LinkAnh.getText().toString();
                Chitiet.getText().toString();
                final AlertDialog.Builder builder = new AlertDialog.Builder(ThemGiayActivity.this);
                builder.setTitle("Thêm dữ liệu");
                builder.setMessage("Bạn có thực sự muốn gửi không?");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ThemGiayActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        databaseHelper.UpData("Insert into Sanpham1 Values(null,'"+Ten.getText().toString()+"','" + Gia.getText().toString() + "','" + SoLuong.getText().toString() + "', '"+LinkAnh.getText().toString()+"', '"+Chitiet.getText().toString()+"')");
                        intent = new Intent(ThemGiayActivity.this, MainActivity.class);
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
        });
//        btnhuy2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(ThemGiayActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
    }
    private class LoadImage extends AsyncTask<String,Void, Bitmap> {
        ImageView imageView;

        public LoadImage(ImageView ivResult) {
            this.imageView=ivResult;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String urlLink=strings[0];
            Bitmap bitmap=null;
            try {
                InputStream inputStream=new java.net.URL(urlLink).openStream();
                bitmap= BitmapFactory.decodeStream(inputStream);

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