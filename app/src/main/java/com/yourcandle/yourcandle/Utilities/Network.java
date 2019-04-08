package com.yourcandle.yourcandle.Utilities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;

import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.yourcandle.yourcandle.Utilities.URLs.SCHEME;
import static com.yourcandle.yourcandle.Utilities.URLs.appendPath1;
import static com.yourcandle.yourcandle.Utilities.URLs.appendPath2;
import static com.yourcandle.yourcandle.Utilities.URLs.authority;
import static com.yourcandle.yourcandle.Utilities.URLs.key;
import static com.yourcandle.yourcandle.Utilities.URLs.value;

/**
 * Created by new on 4/17/2018.
 */

public class Network extends BroadcastReceiver {

    SweetAlertDialog sweetAlertDialog;

    public String buildURL(String fileName, Map<String, String> params) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(SCHEME)
                .authority(authority)
                .appendPath(appendPath1)
                .appendPath(appendPath2)
                .appendPath(fileName)
                .appendQueryParameter(key, value);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        String myUrl = builder.build().toString();
        return myUrl;
    }

    public String buildURLWithoutParam(String fileName) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(SCHEME)
                .authority(authority)
                .appendPath(appendPath1)
                .appendPath(appendPath2)
                .appendPath(fileName)
                .appendQueryParameter(key, value);
        String myUrl = builder.build().toString();
        return myUrl;
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            if (cm.getActiveNetworkInfo() != null) {
                if (sweetAlertDialog != null)
                    sweetAlertDialog.dismiss();
            } else {
                sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
                sweetAlertDialog.setTitleText("Oops...")
                        .setContentText("Unable to connect with the server. Check your internet connection and try again !")
                        .hideConfirmButton()
                        .show();
                sweetAlertDialog.setCancelable(false);
            }
        }
    }

    public void register(BroadcastReceiver receiver, Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    public void unregister(BroadcastReceiver receiver, Context context) {
        try {
            context.unregisterReceiver(receiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}
