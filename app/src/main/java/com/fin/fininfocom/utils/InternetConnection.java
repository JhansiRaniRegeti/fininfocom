package com.fin.fininfocom.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Html;
import android.view.ContextThemeWrapper;

import com.fin.fininfocom.R;

/**
 * Created by Regeti Jhansi Rani  on 09-03-2023.
 */
public class InternetConnection {

    public static boolean isNetworkAvailable(Activity activity) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void networkDialog(Activity activity){
        new android.app.AlertDialog.Builder(new ContextThemeWrapper(activity, R.style.AlertDialogCustom1))
                .setIcon(R.drawable.splash)
                .setTitle("VPe Marketing Executive ")
                .setMessage("Please Check Your Internet Connection!!")
                .setPositiveButton(Html.fromHtml("<font color='#000000'>Okay</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                }).show();
    }
}
