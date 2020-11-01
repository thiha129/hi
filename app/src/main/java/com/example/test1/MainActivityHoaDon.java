package com.example.test1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivityHoaDon extends AppCompatActivity {
    ListView listViewDonHang;
    public static ArrayList<gioHang> arraygioHang;
    public static GioHangAdapTer gioHangAdapTer;
    public static DatabaseHelper databaseHelper;
    public static DatabaseLogin databaseLogin;
    TextView Payy, conut;
    TextView dt, sdt, ten;
    Button btn;
    Intent intent;
    TextView Date_Tao;

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
              intent= new Intent(MainActivityHoaDon.this, MainActivityGioHang.class);
              startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hoa_don);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Date_Tao = findViewById(R.id.Ngay_hoaDon);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a, dd/MM/yyyy");
        String dateTime  =   simpleDateFormat.format(calendar.getTime());
        Date_Tao.setText(dateTime);
        databaseHelper = new DatabaseHelper(MainActivityHoaDon.this, "giaydep", null, 1);
        databaseLogin = new DatabaseLogin(MainActivityHoaDon.this, "mail.sqlite", null, 1);
        MainActivityHoaDon.databaseLogin.UpData("CREATE TABLE IF NOT EXISTS TaiKhoan6 (Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten COLLATE NOCASE, Pass VARCHAR(200), Hovaten COLLATE NOCASE, SoDienThoai VARCHAR(11),Ngay VARCHAR(20), diachi VARCHAR(200))");
        databaseLogin.UpData("CREATE TABLE IF NOT EXISTS HoaDon (Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(200), SoDienThoai VARCHAR(11), diachi VARCHAR(200),soluong integer,tong integer,Ngay VARCHAR(20))");

        arraygioHang = new ArrayList<>();
        AnhXa();
        InfoUser();
        if (ten.length() > 0 && sdt.length() > 0 && dt.length() > 0) {
            ten.setEnabled(false);
            sdt.setEnabled(false);
            dt.setEnabled(false);
        } else {
            ten.setEnabled(true);
            sdt.setEnabled(true);
            dt.setEnabled(true);

        }
        final LoadingDialog_pr loadingDialog_pr = new LoadingDialog_pr(MainActivityHoaDon.this);

        btn = findViewById(R.id.DatHang);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivityHoaDon.this);
                alertDialog.setTitle("Thông báo");
                alertDialog.setIcon(R.drawable.icons8_error_xanh);
                alertDialog.setMessage("Bạn có muốn chắc thanh toán hóa đơn này không >");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        loadingDialog_pr.startLoadingDialog();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialog_pr.dismissDialog();
                                databaseLogin.UpData("Insert into HoaDon values(null,'" + ten.getText() + "','" + sdt.getText() + "','" + dt.getText() + "', '" + conut.getText() + "', '" + Payy.getText() + "','" + Date_Tao.getText() + "' )");
                                databaseHelper.UpData("delete from GioHang2 ");
                                arraygioHang.clear();
                                AnhXa();
                                intent = new Intent(MainActivityHoaDon.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }, 2000);
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                alertDialog.show();


            }
        });
    }

    private void InfoUser() {
        dt = findViewById(R.id.edt_Address);
        sdt = findViewById(R.id.edt_number);
        ten = findViewById(R.id.edt_NameUser);
        Intent i = getIntent();
        int Id = i.getIntExtra("IdUser", 1);
        Cursor cursor = databaseLogin.GetData("SELECT * FROM TaiKhoan6 where Id = " + Id + "");
        while (cursor.moveToNext()) {
            String TenMoi = cursor.getString(3);
            ten.setText(TenMoi);
            String NewNumber = cursor.getString(4);
            sdt.setText(NewNumber);
            String NewAdd = cursor.getString(6);
            dt.setText(NewAdd);
        }
    }

    private void AnhXa() {
        Payy = findViewById(R.id.Pay);
        conut = findViewById(R.id.Count_hoadon);
        listViewDonHang = findViewById(R.id.lishoadon);
        int TinhTong = 0;
        gioHangAdapTer = new GioHangAdapTer(MainActivityHoaDon.this, arraygioHang, R.layout.list_item_giaodiengiohang);
        listViewDonHang.setAdapter(gioHangAdapTer);
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
            conut.setText("" + gioHangAdapTer.getCount());
        }
        int tongtien = TinhTong + 10;
        Payy.setText("$" + tongtien);


    }
}