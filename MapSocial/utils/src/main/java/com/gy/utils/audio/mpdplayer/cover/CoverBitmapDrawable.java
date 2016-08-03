package com.gy.utils.audio.mpdplayer.cover;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import java.io.InputStream;

public class CoverBitmapDrawable extends BitmapDrawable {

	public CoverBitmapDrawable(Resources resources, Bitmap bitmap) {
		super(resources, bitmap);
	}

	public CoverBitmapDrawable(Resources resources, InputStream is) {
		super(resources, is);
	}

	public CoverBitmapDrawable(Resources resources, String string) {
		super(resources, string);
	}
}
