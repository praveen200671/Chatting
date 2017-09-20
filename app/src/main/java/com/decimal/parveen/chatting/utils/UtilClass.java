package com.decimal.parveen.chatting.utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by PSAINI on 9/20/2017.
 */

public class UtilClass {

    public static void showToast(Context context, String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
    public static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm a");
        String strDate = mdformat.format(calendar.getTime());
       return strDate;
    }
}
