package com.example.test1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String ten = intent.getExtras().getString("Tille");
        String nd =  intent.getExtras().getString("Message");
        String chuoi_string = intent.getExtras().getString("extra");
        Intent intent1 = new Intent(context,Music.class);
        intent1.putExtra("extra",chuoi_string);
        intent1.putExtra("Tille",ten);
        intent1.putExtra("Message",nd);
        context.startService(intent1);
    }
}
