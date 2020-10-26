package com.example.test1;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class LoadingDialog_connet {

   private Activity activity;
   private AlertDialog dialog;

    public LoadingDialog_connet(Activity myActivity){
          activity = myActivity;
    }


    public void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);


        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog_2, null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    public void dismissDialog(){
        dialog.dismiss(); 
    }

}
