package com.geno1024.cleaner;

import android.app.*;

public class BaseApplication extends Application
{
	private static BaseApplication instance;
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		instance = this;
	}
	
	public static Application getApplication()
	{
		return instance;
	}
}
