package com.geno1024.cleaner;

import android.content.*;
import android.util.*;
import android.view.*;
import android.app.*;

public class Utils
{
	public static int dp2px(int px)
	{
		DisplayMetrics metrics = new DisplayMetrics();
		((WindowManager) (BaseApplication.getApplication().getSystemService(Context.WINDOW_SERVICE))).getDefaultDisplay().getMetrics(metrics);
		return (int) (metrics.density * 16.0);
	}
}
