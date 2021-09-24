package com.su.primeavto.util;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Connectivity {


    public enum Connection {WIFI, DATA, OFFLINE}


    private static ConnectivityManager sConnectivityManager;


    /**
     * Initialize this class with Application context before using.
     */
    public static void init(final Application application) {

        sConnectivityManager = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
    }


    public static boolean isOnline() {
        NetworkInfo info = sConnectivityManager.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

    public static boolean isOnline(final boolean wifiOnly) {
        NetworkInfo info = sConnectivityManager.getActiveNetworkInfo();
        boolean online = false;

        if (info != null) {
            online = info.isConnectedOrConnecting();
            if (wifiOnly)
                online = info.getType() == ConnectivityManager.TYPE_WIFI;
        }

        return online;
    }


    public static boolean isType(final Connection connection) {
        return connection.equals(getType());
    }


    public static Connection getType() {
        NetworkInfo info = sConnectivityManager.getActiveNetworkInfo();
        if (info != null && info.isConnectedOrConnecting())
            switch (info.getType()) {
                case ConnectivityManager.TYPE_MOBILE:
                    return Connection.DATA;
                case ConnectivityManager.TYPE_WIFI:
                    return Connection.WIFI;
            }
        return Connection.OFFLINE;
    }


    private Connectivity() {
    }
}