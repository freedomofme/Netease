package com.hhxplaying.neteasedemo.netease.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

/**
 * 屏幕工具
 * 
 * @author LynnChurch
 * @version 创建时间：2015年1月15日 下午6:18:44
 * 
 */
public class ScreenUtil
{
	static DisplayMetrics dm = new DisplayMetrics();

	private static void setDisplayMetrics(Activity activity)
	{
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
	}

	public static int getWidth(Activity activity)
	{
		setDisplayMetrics(activity);
		return dm.widthPixels;
	}

	public static int getHeight(Activity activity)
	{
		setDisplayMetrics(activity);
		return dm.heightPixels;
	}

	public static int px2dp(float pxValue, float scale)
	{
		return (int) (pxValue / scale + 0.5f);
	}

	public static int px2dp(float pxValue)
	{
		return (int) (pxValue / dm.scaledDensity + 0.5f);
	}

	public static int dp2px(Activity activity, float dp)
	{
		setDisplayMetrics(activity);
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				activity.getResources().getDisplayMetrics());
	}

	public static int getSW(Activity activity)
	{
		setDisplayMetrics(activity);
		return px2dp(dm.widthPixels, dm.scaledDensity);
	}

	public static int getWidth(Application application) {
		ContextWrapper ctx=new ContextWrapper(application);
		WindowManager wm = (WindowManager)ctx.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();

		return display.getWidth();
	}

	public static int getHeight(Application application) {
		ContextWrapper ctx=new ContextWrapper(application);
		WindowManager wm = (WindowManager)ctx.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();

		return display.getHeight();
	}

	private static void setDisplayMetrics(Context context)
	{
		dm = context.getResources().getDisplayMetrics();
	}

	public static int dp2px(Context context, float dp)
	{
		setDisplayMetrics(context);
		return (int)(dm.density * dp);
	}

	public static float getDensity(Context context) {
		setDisplayMetrics(context);
		return (int)(dm.density);
	}

}
