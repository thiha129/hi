package com.example.test1;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class LoadingDialog_pr {

   private Activity activity;
   private AlertDialog dialog;

    public LoadingDialog_pr(Activity myActivity){
          activity = myActivity;
    }


    public void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);


        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dig_log_camon, null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    public void dismissDialog(){
        dialog.dismiss(); 
    }

}
