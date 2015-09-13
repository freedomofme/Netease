package com.hhxplaying.neteasedemo.netease.fragment;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hhxplaying.neteasedemo.netease.R;
import com.hhxplaying.neteasedemo.netease.config.URLs;
import com.hhxplaying.neteasedemo.netease.util.ScreenUtil;
import com.shizhefei.fragment.LazyFragment;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.IndicatorViewPager.IndicatorFragmentPagerAdapter;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

public class FirstLayerFragment extends LazyFragment {
	private IndicatorViewPager indicatorViewPager;
	private LayoutInflater inflate;
	public static final String INTENT_STRING_TABNAME = "intent_String_tabname";
	public static final String INTENT_INT_INDEX = "intent_int_index";
	private int index;
	private final int textPadding = 20;//dp
	private final int barWidth = 42;//dp

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_tabmain);
		Resources res = getResources();

		Bundle bundle = getArguments();
//		tabName = bundle.getString(INTENT_STRING_TABNAME);
		index = bundle.getInt(INTENT_INT_INDEX);

		ViewPager viewPager = (ViewPager) findViewById(R.id.fragment_tabmain_viewPager);
		ScrollIndicatorView indicator = (ScrollIndicatorView) findViewById(R.id.fragment_tabmain_indicator);
		ColorBar colorBar = new ColorBar(getApplicationContext(), Color.RED, 5);
		colorBar.setWidth(ScreenUtil.dp2px(getActivity(), barWidth));
		indicator.setScrollBar(colorBar);

		float unSelectSize = 14;
		float selectSize = unSelectSize * 1f;//keep same

		int selectColor = res.getColor(R.color.tab_top_text_2);
		int unSelectColor = res.getColor(R.color.tab_top_text_1);
		indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColor, unSelectColor).setSize(selectSize, unSelectSize));

		viewPager.setOffscreenPageLimit(4);

		indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
		inflate = LayoutInflater.from(getApplicationContext());

		// 注意这里 的FragmentManager 是 getChildFragmentManager(); 因为是在Fragment里面
		// 而在activity里面用FragmentManager 是 getSupportFragmentManager()
		indicatorViewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

		Log.d("cccc", "Fragment 将要创建View " + this);
	}

	@Override
	protected void onResumeLazy() {
		super.onResumeLazy();
		Log.d("cccc", "Fragment所在的Activity onResume, onResumeLazy " + this);
	}

	@Override
	protected void onFragmentStartLazy() {
		super.onFragmentStartLazy();
		Log.d("cccc", "Fragment 显示 " + this);
	}

	@Override
	protected void onFragmentStopLazy() {
		super.onFragmentStopLazy();
		Log.d("cccc", "Fragment 掩藏 " + this);
	}

	@Override
	protected void onPauseLazy() {
		super.onPauseLazy();
		Log.d("cccc", "Fragment所在的Activity onPause, onPauseLazy " + this);
	}

	@Override
	protected void onDestroyViewLazy() {
		super.onDestroyViewLazy();
		Log.d("cccc", "Fragment View将被销毁 " + this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d("cccc", "Fragment 所在的Activity onDestroy " + this);
	}

	private class MyAdapter extends IndicatorFragmentPagerAdapter {

		public MyAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		@Override
		public int getCount() {
			return URLs.tabName.length;
		}

		@Override
		public View getViewForTab(int position, View convertView, ViewGroup container) {
			if (convertView == null) {
				convertView = inflate.inflate(R.layout.tab_top, container, false);
			}
			TextView textView = (TextView) convertView;
			textView.setText(URLs.tabName[position]);
			textView.setPadding(ScreenUtil.dp2px(getActivity(), textPadding), 0, ScreenUtil.dp2px(getActivity(), textPadding), 0);
			return convertView;
		}

		@Override
		public Fragment getFragmentForPage(int position) {
			SecondLayerFragment mainFragment = new SecondLayerFragment();
			Bundle bundle = new Bundle();
			bundle.putString(SecondLayerFragment.INTENT_STRING_TABNAME, URLs.tabName[position]);
			bundle.putInt(SecondLayerFragment.INTENT_INT_POSITION, position);
			mainFragment.setArguments(bundle);
			return mainFragment;
		}
	}

}
