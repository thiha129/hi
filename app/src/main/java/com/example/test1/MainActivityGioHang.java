package com.example.test1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivityGioHang extends AppCompatActivity {
    ListView listViewGioHang;
    public static ArrayList<gioHang> arraygioHang;
    public static GioHangAdapTer gioHangAdapTer;
    public static DatabaseHelper databaseHelper;
    TextView Cont, Sum, Pay;
    int indexItem;
     Button Thanks;
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
        setContentView(R.layout.activity_main_gio_hang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listViewGioHang = findViewById(R.id.GioHang);
        Cont = findViewById(R.id.cont);
        Sum = findViewById(R.id.sum);
        Pay = findViewById(R.id.pay);
        Thanks = findViewById(R.id.thank);
        databaseHelper = new DatabaseHelper(MainActivityGioHang.this, "giaydep", null, 1);
        arraygioHang = new ArrayList<>();
        Upload();


//        Toast.makeText(this, "Tinhs toong " + TinhTong, Toast.LENGTH_SHORT).show();

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
                        databaseHelper.UpData("delete from GioHang2 where Id = " + arraygioHang.get(indexItem).getId() + "");
                        arraygioHang.clear();
                        Upload();
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
        Thanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityGioHang.this);
                final LoadingDialog_pr loadingDialog_pr = new LoadingDialog_pr(MainActivityGioHang.this);

                builder.setTitle(R.string.app_name);
                builder.setMessage("Bạn có muốn thanh toán không?");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        databaseHelper.UpData("delete from GioHang2 ");
                        arraygioHang.clear();
                        Upload();
                        Toast.makeText(MainActivityGioHang.this, "Cảm ơn bạn đã mua hàng !", Toast.LENGTH_SHORT).show();
                        loadingDialog_pr.startLoadingDialog();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialog_pr.dismissDialog();
                                intent = new Intent(MainActivityGioHang.this, MainActivity.class);
                                startActivity(intent);
                            }
                        },2000);

                    }
                });
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.create().show();
            }
        });
    }

    private void Upload() {
        int TinhTong = 0;
        gioHangAdapTer = new GioHangAdapTer(MainActivityGioHang.this, arraygioHang, R.layout.list_item_giaodiengiohang);
        listViewGioHang.setAdapter(gioHangAdapTer);
        Cursor cursor = databaseHelper.GetData("Select * from GioHang2");
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
        Sum.setText("$" + TinhTong);
        Pay.setText("$" + TinhTong);
    }

}