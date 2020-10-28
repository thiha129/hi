package com.example.test1;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Music extends Service {
    private static final String CHANNEL_ID = "1";
    //    MediaPlayer mediaPlayer;
    int Id;
//    private String CHANNEL_ID;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("Tille");
        String input2 = intent.getStringExtra("Message");
        String nhankey = intent.getExtras().getString("extra");
        if (nhankey.equals("on")){
            Id = 1;
        }          else if(nhankey.equals("off")){
            Id = 0 ;
        }
        if (Id ==1){
            Intent intent1 = new Intent(this, MainActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent1, 0);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_baseline_add_circle_24)
                    .setContentTitle(input)
                    .setContentText(input2)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    // Set the intent that will fire when the user taps the notification
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);


            //notifi
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(1, builder.build());

//            mediaPlayer.start();
            Id = 0 ;
        }   else if ( Id == 0 ){
//             mediaPlayer.stop();
//             mediaPlayer.reset();
        }

        return START_NOT_STICKY;
    }
}
