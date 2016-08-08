package com.mapsocial.constant;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by ganyu on 2016/8/4.
 */
public class Consts {

    private static Typeface tfGril; //字体为化康少女
    public static Typeface getTypefaceGirl (Context context) {
        if (tfGril == null) tfGril = Typeface.createFromAsset(context.getAssets(), "fonts/girl.ttf");
        return tfGril;
    }

    public String getString (Context context, int id) {
        return context.getResources().getString(id);
    }
}
