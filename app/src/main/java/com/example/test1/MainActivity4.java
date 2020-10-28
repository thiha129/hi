package com.example.test1;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {
    Button btnhengio, btndunglai;
    TextView textViewHienThi;
    TimePicker timePicker;
    Calendar calendar;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    EditText tille, message;
    ListView listViewNoti;
    public static DatabaseHelper databaseHelper;
    public static ArrayList<noti> arrayNoti;
    public static NotiAdapter adapter;

    @SuppressLint("ServiceCast")
    @RequiresApi(api = Build.VERSION_CODES.N)
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnhengio = findViewById(R.id.btnHenGio);
        btndunglai = findViewById(R.id.btnDungLai);
        textViewHienThi = findViewById(R.id.txtview);
        timePicker = findViewById(R.id.timePicker);
        tille = findViewById(R.id.editText2);
        message = findViewById(R.id.editText3);
        listViewNoti = findViewById(R.id.ltNoti);
        arrayNoti = new ArrayList<>();
        adapter = new NotiAdapter(MainActivity4.this, arrayNoti, R.layout.list_item_noti);
        listViewNoti.setAdapter(adapter);
        databaseHelper = new DatabaseHelper(MainActivity4.this, "giaydep", null, 1);
        databaseHelper.UpData("CREATE TABLE IF NOT EXISTS Noti(Id INTEGER PRIMARY KEY AUTOINCREMENT,TieuDe Text,NoiDung Text )");


//                adapter = new GiayAchapter(MainActivity4.this, R.layout.list_item_noti, arrayNoti);

//
        calendar = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final Intent intent = new Intent(MainActivity4.this, AlarmReceiver.class);
        btnhengio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());

                int Gio = timePicker.getCurrentHour();
                int Phut = timePicker.getCurrentMinute();

                String string_gio = String.valueOf(Gio);
                String string_phut = String.valueOf(Phut);

                if (Gio > 12) {
                    string_gio = String.valueOf(Gio - 12);
                }
                if (Phut < 10) {
                    string_phut = "0" + String.valueOf(Phut);
                }
                String tile = tille.getText().toString();
                String me = message.getText().toString();
                intent.putExtra("extra", "on");
                intent.putExtra("Tille", tile);
                intent.putExtra("Message", me);

                pendingIntent = PendingIntent.getBroadcast(MainActivity4.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                textViewHienThi.setText("Giờ bạn đạt là " + string_gio + ":" + string_phut);

                databaseHelper.UpData("Insert into Noti Values(null,'" + tille.getText().toString() + "','" + message.getText().toString() + "')");


            }
        });
        btndunglai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewHienThi.setText("Dừng lại ! ");
                alarmManager.cancel(pendingIntent);
                intent.putExtra("extra", "off");
                sendBroadcast(intent);
            }
        });
        Loading();
    }

    private void Loading() {
        adapter = new NotiAdapter(MainActivity4.this, arrayNoti, R.layout.list_item_noti);
        Cursor cursor = databaseHelper.GetData("SELECT * FROM Noti");
        if (cursor != null) {
            while (cursor.moveToNext()) {
//                int id = cursor.getInt(0);
                String Ten = cursor.getString(1);
                String Nd = cursor.getString(2);
                arrayNoti.add(new noti(null, Ten, Nd));
            }
            listViewNoti.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        } else {
//            Toast.makeText(MainActivitySuaXoaSanPham.this, "NOT OK", Toast.LENGTH_SHORT).show();
        }
    }
}