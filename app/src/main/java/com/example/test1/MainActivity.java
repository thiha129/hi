package com.example.test1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public static DatabaseHelper databaseHelper;
    public static ArrayList<Giay> arrayDoVat;
    public static GiayAchapter adapter;
    ImageButton user;
    Intent intent;
    EditText Search;
    GridView gridView;
    SearchView searchView;

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_home, menu);
      

        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        if (searchMenuItem == null) {
            return true;
        }

        searchView = (SearchView) searchMenuItem.getActionView();
        MenuItemCompat.setOnActionExpandListener(searchMenuItem, new MenuItemCompat.OnActionExpandListener() {

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Set styles for expanded state here
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

                }
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Set styles for collapsed state here
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLUE));
                }
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_info:
                startActivity(new Intent(MainActivity.this, MainActivityThongTin.class));
                break;
            case R.id.action_search:
                
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getColor(R.color.colorPrimary));

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
//        ------------------------------------------------------------------------------------------------

        AnhXa();
//        databaseHelper = new DatabaseHelper(MainActivity.this, "giaydep", null, 1);
//        databaseHelper.UpData("CREATE TABLE IF NOT EXISTS Sanpham2(Id INTEGER PRIMARY KEY AUTOINCREMENT,Ten VarChar(150), Gia VarChar(150), SoLuong VarChar(150), LinkAnh Text ,Chitiet VarChar(150), size VarChar(150))");
//        arrayDoVat = new ArrayList<>();
//        user.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                intent = new Intent(MainActivity.this, MainActivityDangNhap.class);
//                startActivity(intent);
//            }
//        });
//        Search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//
//                adapter = new GiayAchapter(MainActivity.this, R.layout.list_item_abc, arrayDoVat);
//
//                Cursor cursor = databaseHelper.GetData("SELECT * FROM Sanpham2 WHERE Ten LIKE '%" + Search.getText().toString().trim() + "%' ");
//                arrayDoVat.clear();
//                if (cursor != null) {
//                    while (cursor.moveToNext()) {
//                        int id = cursor.getInt(0);
//                        String TenGiay = cursor.getString(1);
//                        String Gia = cursor.getString(2) + "$";
//                        String Soluong = cursor.getString(3);
//                        String LinkAnh = cursor.getString(4);
//                        String Chitiet = cursor.getString(5);
//
//                        arrayDoVat.add(new Giay(id, TenGiay, Gia, Soluong, LinkAnh, Chitiet));
//                    }
//                    gridView.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
    }

    private void AnhXa() {
//        user = findViewById(R.id.imageButton);
//        Search = findViewById(R.id.editText);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}