package com.maiajam.sofa.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Util {

    public static boolean CheckConnection(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();

        return info != null
                && info.isConnectedOrConnecting()
                && cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public static String GetApiToken(Context context) {

        SharedPreferences sh = context.getSharedPreferences("Sofra", context.MODE_PRIVATE);
        String _apiToken = sh.getString("com.mai.sofra.ApiToken", "");

        return _apiToken;
    }

}
