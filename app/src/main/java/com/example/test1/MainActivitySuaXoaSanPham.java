package com.example.test1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivitySuaXoaSanPham extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    GridView gridView;
    public static ArrayList<Giay> arrayDoVat;
    public static GiayAchapter adapter;
    int indexItem;
    Button submit, edit;
    Intent intent;
    private int indext = -1;
    public static   Button btnthem2, btnhuy2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sua_xoa_san_pham);
        gridView = findViewById(R.id.danhsachsp);
        submit = findViewById(R.id.button);
        edit = findViewById(R.id.button2);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivitySuaXoaSanPham.this, ThemGiayActivity.class);
                startActivity(intent);
            }
        });
        databaseHelper = new DatabaseHelper(MainActivitySuaXoaSanPham.this, "giaydep", null, 1);
        databaseHelper.UpData("CREATE TABLE IF NOT EXISTS Sanpham2(Id INTEGER PRIMARY KEY AUTOINCREMENT,Ten VarChar(150), Gia VarChar(150), SoLuong VarChar(150), LinkAnh Text ,Chitiet VarChar(150), size VarChar(150))");
        arrayDoVat = new ArrayList<>();
        abc02();
        Xoa();
        Sua();

    }

    private void Sua() {
        final GridView gridView = findViewById(R.id.danhsachsp);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                indext = i;
                Intent movetocharacter = new Intent(MainActivitySuaXoaSanPham.this, SuaMainActivity.class);
                movetocharacter.putExtra("id",arrayDoVat.get(i).getId());
                startActivity(movetocharacter);
            }
        });
    }

    private void Xoa() {
        final GridView gridView = findViewById(R.id.danhsachsp);
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                indexItem = i;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivitySuaXoaSanPham.this);
                builder.setTitle(R.string.app_name);
                builder.setMessage("Bạn có muốn xóa không?");

                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        databaseHelper.UpData("delete from Sanpham2 where Id = " + arrayDoVat.get(indexItem).getId() + "");
                        arrayDoVat.clear();
                        abc02();
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
    }
    private void abc02() {
        adapter = new GiayAchapter(MainActivitySuaXoaSanPham.this, R.layout.list_item_abc, arrayDoVat);
        Cursor cursor = databaseHelper.GetData("SELECT * FROM Sanpham2");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String TenGiay = cursor.getString(1);
                String Gia = cursor.getString(2)+"$";
                String Soluong = cursor.getString(3);
                String LinkAnh = cursor.getString(4);
                String Chitiet = cursor.getString(5);
                arrayDoVat.add(new Giay(id, TenGiay, Gia, Soluong, LinkAnh, Chitiet));
            }
            adapter.notifyDataSetChanged();
            gridView.setAdapter(adapter);
        } else {
            Toast.makeText(MainActivitySuaXoaSanPham.this, "NOT OK", Toast.LENGTH_SHORT).show();
        }

    }
}