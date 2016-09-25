package com.runzii.traveling.utils;

import android.widget.Toast;

import com.runzii.traveling.TravelingApplication;

/**
 * Created by Wouldyou on 2015/5/25.
 */
public class Toasts {

    public static void show(String message) {
        Toast.makeText(TravelingApplication.getAppContext(), message, Toast.LENGTH_LONG).show();
    }
}
