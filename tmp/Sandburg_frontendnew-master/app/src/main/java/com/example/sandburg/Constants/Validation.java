package com.example.sandburg.Constants;

import android.text.TextUtils;
import android.view.View;

public class Validation {

    public static boolean isEmpty(String s)
    {
        if(s.trim().length() == 0)
        {
            return true;
        }
        return false;
    }


}
