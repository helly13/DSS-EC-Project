package com.example.sandburg.Constants;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static boolean isInternetConnected(Context mContext) {

        try {
            ConnectivityManager connect = null;
            connect = (ConnectivityManager) mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            if (connect != null) {
                NetworkInfo resultMobile = connect
                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

                NetworkInfo resultWifi = connect
                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                if ((resultMobile != null && resultMobile
                        .isConnectedOrConnecting())
                        || (resultWifi != null && resultWifi
                        .isConnectedOrConnecting())) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {

        }

        return false;
    }

    public static void setPref(Context c, String pref, String val) {
        try {
            SharedPreferences preferences = c.getSharedPreferences(c.getPackageName(), Context.MODE_PRIVATE);
            SharedPreferences.Editor e = preferences.edit();
            e.putString(pref, val);
            e.apply();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    public static String getPref(Context c, String pref, String val) {
        SharedPreferences preferences = c.getSharedPreferences(c.getPackageName(), Context.MODE_PRIVATE);
        return preferences.getString(pref, val);
    }

    public static void setPref(Context c, String pref, int val) {
        try {
            SharedPreferences preferences = c.getSharedPreferences(c.getPackageName(), Context.MODE_PRIVATE);
            SharedPreferences.Editor e = preferences.edit();
            e.putInt(pref, val);
            e.apply();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    public static int getPref(Context c, String pref, int val) {
        SharedPreferences preferences = c.getSharedPreferences(c.getPackageName(), Context.MODE_PRIVATE);
        return preferences.getInt(pref, val);
    }


    public static void setPref(Context c, String pref, long val) {

        try {
            SharedPreferences preferences = c.getSharedPreferences(c.getPackageName(), Context.MODE_PRIVATE);
            SharedPreferences.Editor e = preferences.edit();
            e.putLong(pref, val);
            e.apply();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    public static long getPref(Context c, String pref, long val) {

        SharedPreferences preferences = c.getSharedPreferences(c.getPackageName(), Context.MODE_PRIVATE);
        return preferences.getLong(pref, val);
    }


    public static void setPref(Context c, String pref, Boolean val) {
        try {
            SharedPreferences preferences = c.getSharedPreferences(c.getPackageName(), Context.MODE_PRIVATE);
            SharedPreferences.Editor e = preferences.edit();
            e.putBoolean(pref, val);
            e.apply();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
    public static String format(long time, String pattern) {

        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        try {
            return sdf.format(new Date(time));
        } catch (Exception e) {

        }

        return "";
    }

    public static boolean getPref(Context c, String pref, Boolean val) {
        SharedPreferences preferences = c.getSharedPreferences(c.getPackageName(), Context.MODE_PRIVATE);
        return preferences.getBoolean(pref, val);
    }

    public static void delPref(Context c, String pref) {
        try {
            SharedPreferences preferences = c.getSharedPreferences(c.getPackageName(), Context.MODE_PRIVATE);
            SharedPreferences.Editor e = preferences.edit();
            e.remove(pref);
            e.apply();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static void ClearAllPreference(Context c) {
        try {
            SharedPreferences preferences = c.getSharedPreferences(c.getPackageName(), Context.MODE_PRIVATE);
            SharedPreferences.Editor e = preferences.edit();
            e.clear();
            e.apply();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

}
