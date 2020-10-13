package com.example.test1.ui.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.test1.DatabaseHelper;
import com.example.test1.Giay;
import com.example.test1.GiayAchapter;
import com.example.test1.GiayAdapter;
import com.example.test1.MainActivity;
import com.example.test1.R;
import com.example.test1.ThemGiayActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HomeFragment extends Fragment {
    private DatabaseHelper databaseHelper;
    private HomeViewModel homeViewModel;
    GridView gridView;
    Button button, btnthem, btnhuy,btnthem2;
    EditText Ten, Gia,SoLuong;
    EditText etURL;
    Button btnClear, btnSubmit;
    ImageView ivResult;
    View view;
    public static ArrayList<Giay> arrayDoVat;
    public static GiayAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        view = inflater.inflate(R.layout.fragment_home, container, false);
        View view2 = inflater.inflate(R.layout.dialof_them_giay, container, false);
        btnClear=view2.findViewById(R.id.btn_clear);
        btnSubmit=view2.findViewById(R.id.btn_submit);
        etURL=view2.findViewById(R.id.et_ulr);
        ivResult=view2.findViewById(R.id.iv_result);
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
                    Toast.makeText(getActivity(), "Please enter url", Toast.LENGTH_SHORT).show();
                }else {
                    LoadImage loadImages=new LoadImage(ivResult);
                    loadImages.execute(urlLink);
                }
            }
        });

        gridView =  view.findViewById(R.id.lv1);
        button = view.findViewById(R.id.btnthem);
        arrayDoVat =new ArrayList<>();
        adapter=new GiayAdapter(getActivity(),R.layout.list_item_abc, arrayDoVat);
        final Dialog dialog = new Dialog(getActivity());
        btnthem= dialog.findViewById(R.id.btnthem);
        databaseHelper = new DatabaseHelper(getActivity(), "giaydep", null, 1);
        databaseHelper.UpData("CREATE TABLE IF NOT EXISTS Sanpham1(Id INTEGER PRIMARY KEY AUTOINCREMENT,Ten VarChar(150), Gia VarChar(150), SoLuong VarChar(150), LinkAnh VarChar(150),Chitiet VarChar(150))");

        abc02();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "abc", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), ThemGiayActivity.class));
            }
        });
        Xoa();
        return view;
    }

    private void abc02() {
        adapter = new GiayAdapter(getActivity(), R.layout.list_item_abc, arrayDoVat);
        Cursor cursor = databaseHelper.GetData("SELECT * FROM Sanpham1");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String TenGiay = cursor.getString(1);
                String Gia = cursor.getString(2);
                String Soluong = cursor.getString(3);
                String LinkAnh = cursor.getString(4);
                String Chitiet = cursor.getString(5);
                arrayDoVat.add(new Giay(id, TenGiay, Gia, Soluong,LinkAnh,Chitiet));
            }
            gridView.setAdapter(adapter);
        } else {
            Toast.makeText(getActivity(), "NOT OK", Toast.LENGTH_SHORT).show();
        }

    }
//    class ReadJSON extends AsyncTask<String ,Integer,String>{
//
//        @Override
//        protected String doInBackground(String... strings) {
//            StringBuilder content = new StringBuilder();
//            return readURL(strings[0]);
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            try {
//                JSONObject jsonObject=new JSONObject(s);
////                JSONArray jsonArray=new jsonObject.getJSONArray("")
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    private static String readURL(String theURL){
//        StringBuilder content = new StringBuilder();
//        try {
//            URL url=new URL(theURL);
//            URLConnection urlConnection=url.openConnection();
//            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//            String line;
//            while ((line=bufferedReader.readLine())!=null){
//                content.append((line+"\n"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        return content.toString();
//    }


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
                        databaseHelper.UpData("delete from Sanpham1 where Id = " + arrayDoVat.get(indexItem).getId() + "");
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