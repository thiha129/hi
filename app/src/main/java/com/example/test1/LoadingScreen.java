package com.example.test1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoadingScreen extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;
    ConnectivityManager connectivityManager;
    NetworkInfo mywifi, my3G;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar = findViewById(R.id.progress_bar);
        textView = findViewById(R.id.text_view);

        progressBar.setMax(100);
        final LoadingDialog_connet loadingDialog = new LoadingDialog_connet(LoadingScreen.this);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWF = networkInfo.isConnected();
        networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean is4G = networkInfo.isConnected();
        
        if (isWF) {
//            Toast.makeText(this, "WIFI", Toast.LENGTH_SHORT).show();
            progressAnimation();
//            restartActivity(LoadingScreen.this);
        } else if (is4G) {
            progressAnimation();
//            Toast.makeText(this, "4G", Toast.LENGTH_SHORT).show();
        } else {
            loadingDialog.startLoadingDialog();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
//                    loadingDialog.dismissDialog();
                    restartActivity(LoadingScreen.this);
                }
            },5000);


        }

    }
    private void restartActivity(Activity act) {
        final LoadingDialog_connet loadingDialog = new LoadingDialog_connet(LoadingScreen.this);

        Intent intent = new Intent();
        intent.setClass(act, act.getClass());
        act.startActivity(intent);
        act.finish();
//        loadingDialog.startLoadingDialog();
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                loadingDialog.dismissDialog();
//                Toast.makeText(LoadingScreen.this, "Không bật mạng", Toast.LENGTH_SHORT).show();
//
//
//            }
//        },10000);
    }
    public void progressAnimation() {


        ProgressBarAnimation anim = new ProgressBarAnimation(this, progressBar, textView, 0f, 100f);
        anim.setDuration(3000);
        progressBar.setAnimation(anim);
    }
}