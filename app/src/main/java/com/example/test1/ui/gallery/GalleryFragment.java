package com.example.test1.ui.gallery;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.test1.DatabaseHelper;
import com.example.test1.Giay;
import com.example.test1.GiayAchapter;
import com.example.test1.R;

import java.util.ArrayList;

public class GalleryFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {

    private GalleryViewModel galleryViewModel;
    public static DatabaseHelper databaseHelper;
    GridView gridView;
    public static ArrayList<Giay> arrayDoVat;
    public static GiayAchapter adapter;
    TextView loc;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        gridView = root.findViewById(R.id.lv2);
        databaseHelper = new DatabaseHelper(getActivity(), "giaydep", null, 1);
        databaseHelper.UpData("CREATE TABLE IF NOT EXISTS Sanpham2(Id INTEGER PRIMARY KEY AUTOINCREMENT,Ten VarChar(150), Gia VarChar(150), SoLuong VarChar(150), LinkAnh Text ,Chitiet VarChar(150), size VarChar(150))");
        arrayDoVat = new ArrayList<>();
        adapter = new GiayAchapter(getActivity(), R.layout.list_item_abc, arrayDoVat);
        loc= root.findViewById(R.id.textView12);
        xyz();
        Loc();




        return root;
    }

    private void Loc() {
       loc.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               showPopup(view);
           }
       });
    }

    public void showPopup(View view){
         PopupMenu popup = new PopupMenu(getActivity(),view);
         popup.setOnMenuItemClickListener(this);
         popup.inflate(R.menu.popup_menu);
         popup.show();
     }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.item1:
                Toast.makeText(getActivity(), "ssadasd", Toast.LENGTH_SHORT).show();
            return true;
            default:
                    return false;
        }
    }
    private void xyz() {
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
}