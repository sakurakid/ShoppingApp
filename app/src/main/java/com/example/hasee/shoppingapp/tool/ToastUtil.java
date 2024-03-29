package com.example.hasee.shoppingapp.tool;

import android.content.Context;
import android.widget.Toast;

/**
 * 封装一下
 */
public class ToastUtil {
    public static void showL(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_LONG).show();
    }

    public static void showS(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }
}
