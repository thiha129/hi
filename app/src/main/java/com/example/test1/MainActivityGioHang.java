package com.example.test1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivityGioHang extends AppCompatActivity {
    ListView listViewGioHang;
    public static ArrayList<gioHang> arraygioHang;
    public static SharedPreferences mysharedPreferences;
    public static ArrayList<Nguoidung> nguoidungArrayList;
    public static GioHangAdapTer gioHangAdapTer;
    public static DatabaseHelper databaseHelper;
    public static DatabaseLogin databaseLogin;
    public static NguoidungAdapter adapter;
    TextView Cont, Sum, Pay, phi;
    int indexItem;
    Button Thanks;
    Intent intent;
    int i = -1;
    TextView Date_Tao;

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
               intent = new Intent(MainActivityGioHang.this, MainActivityThongTinSanPham.class);
               startActivity(intent);
               finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gio_hang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listViewGioHang = findViewById(R.id.GioHang);
        Cont = findViewById(R.id.cont);
        Sum = findViewById(R.id.sum);
        Pay = findViewById(R.id.pay);
        Thanks = findViewById(R.id.thank);
        phi = findViewById(R.id.phichuyen);
        mysharedPreferences = getSharedPreferences("mylogin", MODE_PRIVATE);

        databaseHelper = new DatabaseHelper(MainActivityGioHang.this, "giaydep", null, 1);
        databaseLogin = new DatabaseLogin(MainActivityGioHang.this, "mail.sqlite", null, 1);
        MainActivityGioHang.databaseLogin.UpData("CREATE TABLE IF NOT EXISTS TaiKhoan6 (Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten COLLATE NOCASE, Pass VARCHAR(200), Hovaten COLLATE NOCASE, SoDienThoai VARCHAR(11),Ngay VARCHAR(20), diachi VARCHAR(200))");
        databaseLogin.UpData("CREATE TABLE IF NOT EXISTS HoaDon (Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(200), SoDienThoai VARCHAR(11), diachi VARCHAR(200),soluong integer,tong integer,Ngay VARCHAR(20))");

        arraygioHang = new ArrayList<>();
        Upload();

        listViewGioHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                indexItem = i;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityGioHang.this);
                builder.setTitle(R.string.app_name);
                builder.setMessage("Bạn có muốn xóa không?");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        databaseHelper.UpData("delete from GioHang3 where Id = " + arraygioHang.get(indexItem).getId() + "");
                        arraygioHang.clear();
                        Upload();
                        Toast.makeText(MainActivityGioHang.this, "Bạn đã xoá sản phẩm này ra khỏi giỏ hàng !", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.create().show();
                return false;
            }
        });
//        Chuyen trang
        Thanks.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                ChuyenTrang();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void ChuyenTrang() {

        Date_Tao = findViewById(R.id.Ngay);
//        ---------------------------------------------------------------------------
//        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        final String NgayGui = simpleDateFormat.format(date);
//        Date_Tao.setText(NgayGui);
//        ----------------------------------------------------------------------------
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a, dd/MM/yyyy");
        String dateTime = simpleDateFormat.format(calendar.getTime());
        Date_Tao.setText(dateTime);

        final LoadingDialog loadingDialog = new LoadingDialog(MainActivityGioHang.this);
        Cursor cursor1 = databaseLogin.GetData("SELECT * FROM TaiKhoan6 ");
        while (cursor1.moveToNext()) {
            final int id = cursor1.getInt(0);
            cursor1.getString(3);
            cursor1.getString(4);
            cursor1.getString(6);

            loadingDialog.startLoadingDialog();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadingDialog.dismissDialog();
                    intent = new Intent(MainActivityGioHang.this, MainActivityHoaDon.class);
                    String islogin = mysharedPreferences.getString("username", "");
                    if (islogin != "") {
                        intent.putExtra("IdUser", id);
                        startActivity(intent);
                    } else {
                        startActivity(intent);
                    }
                }
            }, 2000);
        }
    }

    private void Upload() {
        int TinhTong = 0;
        gioHangAdapTer = new GioHangAdapTer(MainActivityGioHang.this, arraygioHang, R.layout.list_item_giaodiengiohang);
        listViewGioHang.setAdapter(gioHangAdapTer);
        Cursor cursor = databaseHelper.GetData("Select * from GioHang3");
        while (cursor.moveToNext()) {
            arraygioHang.add(new gioHang(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getString(4))
            );
            TinhTong += cursor.getInt(2);
            Cont.setText("" + gioHangAdapTer.getCount());
        }
        gioHangAdapTer.notifyDataSetChanged();
        if (gioHangAdapTer.getCount() < 1) {
            Cont.setText("0");
            Sum.setText("$0");
            Pay.setText("$0");
            phi.setText("0");
            Thanks.setEnabled(false);
        } else {
            Sum.setText("$" + TinhTong);
            int tongtien = TinhTong + 10;
            Pay.setText("$" + tongtien);
            phi.setText("$10");
            Thanks.setEnabled(true);
        }
    }
}