package com.example.test1.ui.home;

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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test1.DatabaseHelper;
import com.example.test1.Giay;
import com.example.test1.GiayAchapter;
import com.example.test1.GioHangAdapTer;
import com.example.test1.MainActivityGioHang;
import com.example.test1.MainActivityThongTinSanPham;
import com.example.test1.MainAdapter;
import com.example.test1.MainAdapter_Web;
import com.example.test1.MainModel;
import com.example.test1.MainModel_web;
import com.example.test1.R;
import com.example.test1.gioHang;
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
    TextView Gh;
    public static ArrayList<Giay> arrayDoVat;
    public static GiayAchapter adapter;
    RecyclerView recyclerView, recyclerView_Web;
    ArrayList<MainModel> mainModels;
    MainAdapter mainAdapter;
    ArrayList<MainModel_web> mainModel_webs;
    MainAdapter_Web mainAdapter_web;
    public static GioHangAdapTer gioHangAdapTer;
    public static ArrayList<gioHang> arraygioHang;
    private int indext = -1;
    private AdView mAdView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        view = inflater.inflate(R.layout.fragment_home, container, false);
        gridView = view.findViewById(R.id.lv1);
        databaseHelper = new DatabaseHelper(getActivity(), "giaydep", null, 1);
        databaseHelper.UpData("CREATE TABLE IF NOT EXISTS Sanpham3(Id INTEGER PRIMARY KEY AUTOINCREMENT,Ten VarChar(150), Gia VarChar(150), SoLuong VarChar(150), LinkAnh Text ,Chitiet VarChar(150), size VarChar(150))");
        databaseHelper.UpData("CREATE TABLE IF NOT EXISTS GioHang3 (Id INTEGER PRIMARY KEY AUTOINCREMENT,Ten VarChar(150), Gia integer,size integer,LinkAnh Text )");

        arrayDoVat = new ArrayList<>();
        adapter = new GiayAchapter(getActivity(), R.layout.list_item_abc, arrayDoVat);
        abc02();
        abc03();
        abc04();
        abc4();
        QuangCao();
        arraygioHang = new ArrayList<>();
        hienthi();
        return view;

    }

    private void hienthi() {
        Gh = view.findViewById(R.id.txtSoluongSp);
        gioHangAdapTer = new GioHangAdapTer(getActivity(), arraygioHang, R.layout.list_item_giaodiengiohang);
        Cursor cursor = databaseHelper.GetData("Select * from GioHang3");
        while (cursor.moveToNext()) {
            arraygioHang.add(new gioHang(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getString(4))
            );
            Gh.setText("(" + gioHangAdapTer.getCount() + ")");
        }
        Gh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainActivityGioHang.class));
            }
        });
    }

    private void QuangCao() {
        mAdView = view.findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void abc04() {
        recyclerView_Web = view.findViewById(R.id.recyclerview_web);
        Integer[] langLogo = {
                R.drawable.web1,
                R.drawable.web2,
                R.drawable.web4,
                R.drawable.web5,
                R.drawable.web6,
                R.drawable.web3,
        };
        String[] langname = {
                "Sự phát triển thần kì",
                "Lịch sử phát triển công",
                "BẮT KỊP XU HƯỚNG VỚI",
                "SNEAKER LÀ GÌ? LIỆT KÊ",
                "GIÀY ADIDAS ALPHABOUNCE XENO",
                "1000 ĐÔI GIÀY ADIDAS EQT"
        };
        String[] langlink = {"https://thethao247.vn/318-su-phat-trien-than-ki-cua-cong-nghe-nike-flyknit-d177599.html",
                "https://thethao247.vn/318-lich-su-phat-trien-cong-nghe-dem-nike-air-max-d177454.html",
                "https://giayadidas.com.vn/cac-mau-giay-adidas-moi-nhat/",
                "https://giayadidas.com.vn/giay-sneaker-la-gi/",
                "https://giayadidas.com.vn/giay-adidas-alphabounce-xeno-mot-thiet-ke-khac-la/",
                "https://giayadidas.com.vn/1000-doi-giay-adidas-eqt-adv-91-16-art-basel-ra-mat-dot-dau-tien/"
        };
        mainModel_webs = new ArrayList<>();
        for (int i = 0; i < langLogo.length; i++) {
            MainModel_web model_web = new MainModel_web(langLogo[i], langname[i] + "...", langlink[i]);
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
        final Cursor cursor = databaseHelper.GetData("SELECT * FROM Sanpham3");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String TenGiay = cursor.getString(1);
                int Gia = cursor.getInt(2);
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
}