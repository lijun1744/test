package com.example.mylibrary;

import android.content.Context;
import android.widget.Toast;

public class Utils {
    public static void showMe(Context context) {
        Toast.makeText(context, "这是我的第一个开源项目", Toast.LENGTH_SHORT).show();
    }
}
