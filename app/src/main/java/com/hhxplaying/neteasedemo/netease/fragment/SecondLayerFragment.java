package com.hhxplaying.neteasedemo.netease.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.hhxplaying.neteasedemo.netease.R;
import com.hhxplaying.neteasedemo.netease.adapter.NormalRecyclerViewAdapter;
import com.hhxplaying.neteasedemo.netease.bean.OneNewsItemBean;
import com.hhxplaying.neteasedemo.netease.config.Global;
import com.hhxplaying.neteasedemo.netease.config.URLs;
import com.hhxplaying.neteasedemo.netease.factory.RequestSingletonFactory;
import com.hhxplaying.neteasedemo.netease.vollley.MySingleton;
import com.shizhefei.fragment.LazyFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SecondLayerFragment extends LazyFragment implements SwipeRefreshLayout.OnRefreshListener {
	public static final String INTENT_STRING_TABNAME = "intent_String_tabName";
	public static final String INTENT_INT_POSITION = "intent_int_position";
	private String tabName;
	private int position;
	private TextView textView;
    private ArrayList<OneNewsItemBean> mOneNewsItemList = new ArrayList<>();
    private HashSet<String> newsKeySet = new HashSet<>();
	private NormalRecyclerViewAdapter normalRecyclerViewAdapter;

    private RecyclerView mRecyclerView;
	private SwipeRefreshLayout swiper;
	private IndexModel indexModel;
	private LinearLayoutManager llm;

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		tabName = getArguments().getString(INTENT_STRING_TABNAME);
		position = getArguments().getInt(INTENT_INT_POSITION);
		//临时处理下
		position = position % 5;
		indexModel = new IndexModel();

		setContentView(R.layout.fragment_tabmain_item);

		swiper = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
		swiper.setOnRefreshListener(this);
		swiper.setColorSchemeResources(android.R.color.holo_green_light,
				android.R.color.holo_green_dark);

        mRecyclerView = (RecyclerView)findViewById(R.id.rv_recycler_view);
		llm = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(llm);
		normalRecyclerViewAdapter = new NormalRecyclerViewAdapter(getActivity(), mOneNewsItemList, mRecyclerView);
        mRecyclerView.setAdapter(normalRecyclerViewAdapter);
		getIndexNews();
		setOnLoadMoreListerner();
	}

	private void setOnLoadMoreListerner() {
		mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				if (llm.findLastVisibleItemPosition() == mOneNewsItemList.size() - 1 && mOneNewsItemList.size() != 0) {
					Log.d("RVA", "load more");
					getIndexNews();
				}
			}
		});
	}

	@Override
	protected void onResumeLazy() {
		super.onResumeLazy();
//		getIndexNews();
	}

	@Override
	protected void onDestroyViewLazy() {
		super.onDestroyViewLazy();
	}


	private void getIndexNews() {
		while (indexModel.getStart() < mOneNewsItemList.size()) {
			boolean result = indexModel.moveNextIndex();
			Log.d("RVA", "moveNextIndex: " + result);
			if (!result) break;

		}
		Log.d("RVA", "indexModel.getStartEndIndex" + indexModel.getStartEndIndex());

        MySingleton.getInstance(getActivity().getApplicationContext()).getRequestQueue().add(
                RequestSingletonFactory.getInstance().getGETStringRequest(getActivity(),
						URLs.concatNewsListURL(tabName, indexModel.getStartEndIndex()),
						new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        JSONObject obj;
                        try {
                            obj = new JSONObject(response.toString());
                            JSONArray itemArray = obj.getJSONArray(URLs.getUrlTag(tabName));
                            ArrayList<OneNewsItemBean> newsList = new Gson().fromJson(itemArray.toString(), Global.NewsItemType);

                            // 去重
							for (int i = 0; i < newsList.size(); i++) {
								OneNewsItemBean bean = newsList.get(i);

								// banner只允许一个
								if (bean.getTemplate() != null && bean.getTemplate().length() > 0) {
									if (mOneNewsItemList.size() > 0) {
										continue;
									}
								}

								if (!newsKeySet.contains(bean.getPostid())) {
									newsKeySet.add(bean.getPostid());
									mOneNewsItemList.add(bean);
								}
							}

                            Log.d("RVA", "list size:" + mOneNewsItemList.size());
							normalRecyclerViewAdapter.notifyDataSetChanged();
							swiper.setRefreshing(false);
						} catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }));
	}

	@Override
	public void onRefresh() {
		Log.d("RVA", "onRefresh called");
		mOneNewsItemList.clear();
		newsKeySet.clear();
		indexModel.reSet();
		getIndexNews();
	}

	// 控制索引
	static class IndexModel {
		public IndexModel() {
			this.start = 0;
			this.end = 10;
		}

		public int getStart() {
			return start;
		}

		int start; // start with 0
		int end; // [start, end)

		// 索引向前走
		public boolean moveNextIndex() {
			start = end;
			end = getEndIndexByStartIndex(start);
			// 操作最大值了
			if (end == -1) {
				reSet();
				return false;
			}
			return true;
		}

		// 返回请求的字符串
		public String getStartEndIndex() {
			if (end == 200) {
				return end + "-" + start; // 他们接口的bug
			}

			return start + "-" + end;
		}

		public void reSet() {
			this.start = 0;
			this.end = 10;
		}

		/**
		 * 通过传入的StartIndex来计算endIndex，因为他们接口限制了。
		 * @return endIndex;
		 */
		private int getEndIndexByStartIndex(int startIndex) {
			if (startIndex <= 10) {
				return startIndex + 10;
			} else if (startIndex <= 40) {
				return startIndex + 20;
			} else if (startIndex <= 100) {
				return startIndex + 40;
			} else if (startIndex <= 140) {
				return 200;
			}
			return -1;
		}

	}
}
