package com.example.test1.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test1.DatabaseHelper;
import com.example.test1.Giay;
import com.example.test1.GiayAchapter;
import com.example.test1.MainActivityThongTinSanPham;
import com.example.test1.MainAdapter;
import com.example.test1.MainAdapter_Web;
import com.example.test1.MainModel;
import com.example.test1.MainModel_web;
import com.example.test1.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    public static DatabaseHelper databaseHelper;
    private HomeViewModel homeViewModel;
    GridView gridView;
    Button button, btnthem, btnhuy, btnthem2;
    EditText Ten, Gia, SoLuong;
    EditText etURL;
    Button btnClear, btnSubmit;
    ImageView ivResult, giohang;
    View view;
    public static ArrayList<Giay> arrayDoVat;
    public static GiayAchapter adapter;
    RecyclerView recyclerView, recyclerView_Web;
    ArrayList<MainModel> mainModels;
    MainAdapter mainAdapter;
    ArrayList<MainModel_web> mainModel_webs;
    MainAdapter_Web mainAdapter_web;
    private int indext = -1;
    private AdView mAdView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        view = inflater.inflate(R.layout.fragment_home, container, false);
        gridView = view.findViewById(R.id.lv1);
        databaseHelper = new DatabaseHelper(getActivity(), "giaydep", null, 1);
        databaseHelper.UpData("CREATE TABLE IF NOT EXISTS Sanpham2(Id INTEGER PRIMARY KEY AUTOINCREMENT,Ten VarChar(150), Gia VarChar(150), SoLuong VarChar(150), LinkAnh Text ,Chitiet VarChar(150), size VarChar(150))");
        databaseHelper.UpData("CREATE TABLE IF NOT EXISTS GioHang3 (Id INTEGER PRIMARY KEY AUTOINCREMENT,Ten VarChar(150), Gia integer,size integer,LinkAnh Text )");

        arrayDoVat = new ArrayList<>();
        adapter = new GiayAchapter(getActivity(), R.layout.list_item_abc, arrayDoVat);
        abc02();
        abc03();
        abc04();
        abc4();
        QuangCao();
        return view;

    }

    private void QuangCao() {
        mAdView = view.findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void abc04() {
        recyclerView_Web = view.findViewById(R.id.recyclerview_web);
        Integer[] langLogo = {
                R.drawable.web2,
                R.drawable.nike,
                R.drawable.puma,
                R.drawable.fila,
                R.drawable.converse,
                R.drawable.mn,
        };
        String[] langname = {
                "Sự phát triển thần kì",
                "Bag", "Bear", "Shoe", "Bag", "Bear"
        };
        String[] langlink = {"https://thethao247.vn/318-su-phat-trien-than-ki-cua-cong-nghe-nike-flyknit-d177599.html",
                "Bag", "Bear", "Shoe", "Bag", "Bear"
        };
        mainModel_webs = new ArrayList<>();
        for (int i = 0; i < langLogo.length; i++) {
            MainModel_web model_web = new MainModel_web(langLogo[i], langname[i]+"...",langlink[i]);
            mainModel_webs.add(model_web);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_Web.setLayoutManager(layoutManager);
        recyclerView_Web.setItemAnimator(new DefaultItemAnimator());


        mainAdapter_web = new MainAdapter_Web(getActivity(), mainModel_webs);
        recyclerView_Web.setAdapter(mainAdapter_web);
    }

    private void abc03() {
        recyclerView = view.findViewById(R.id.recyclerview);
        Integer[] langLogo = {
                R.drawable.adidas,
                R.drawable.nike,
                R.drawable.puma,
                R.drawable.fila,
                R.drawable.converse,
                R.drawable.mn,
        };
        String[] langname = {
                "https://thethao247.vn/318-su-phat-trien-than-ki-cua-cong-nghe-nike-flyknit-d177599.html",
                "Bag", "Bear", "Shoe", "Bag", "Bear"
        };
        mainModels = new ArrayList<>();
        for (int i = 0; i < langLogo.length; i++) {
            MainModel model = new MainModel(langLogo[i], langname[i]);
            mainModels.add(model);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        mainAdapter = new MainAdapter(getActivity(), mainModels);
        recyclerView.setAdapter(mainAdapter);

    }
    private void abc4() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                indext = i;
                Intent movetocharacter = new Intent(getActivity(), MainActivityThongTinSanPham.class);
                movetocharacter.putExtra("id", arrayDoVat.get(i).getId());
                startActivity(movetocharacter);
            }
        });
    }




    private void abc02() {
        adapter = new GiayAchapter(getActivity(), R.layout.list_item_abc, arrayDoVat);
        final Cursor cursor = databaseHelper.GetData("SELECT * FROM Sanpham2");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String TenGiay = cursor.getString(1);
                String Gia = cursor.getString(2) + "$";
                String Soluong = cursor.getString(3);
                String LinkAnh = cursor.getString(4);
                String Chitiet = cursor.getString(5);

                arrayDoVat.add(new Giay(id, TenGiay, Gia, Soluong, LinkAnh, Chitiet));
            }
            gridView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    int indexItem;

    private void Xoa() {
        final GridView listView = view.findViewById(R.id.lv1);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                indexItem = i;
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
}