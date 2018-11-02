package com.hhxplaying.neteasedemo.netease.widget;

/**
 * Created by HHX on 15/9/9.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhxplaying.neteasedemo.netease.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 大雄O(∩_∩)O~
 * @version 1.0.1
 */
public class SwitchImage extends LinearLayout {
    private final int MOVING = 1;
    private static final int LOOP_TIMES = 2000;
    private int timeGap = 1000;

    private Context mContext;
    private BtnJumpTOWhere btnJumpTOWhere;
    private List<View> viewList = new ArrayList<View>();
    private List<ImageView> imageViewList = new ArrayList<ImageView>();
    private ViewPager mPager;
    private LinearLayout mDotsLayout;
    private TextView mTextView;
    private String texts[];
//    private ImageButton mBtn;

    private boolean moving = false;
    private boolean lastItemToNextOrStop = false;
    private ViewPagerAdapter ViewPagerAdapter;

    private MyHandler handler;
    private SwitchImageOnClick switchImageOnClick;
    private DisplayImageView displayImageView;

    private static class MyHandler extends Handler {
        WeakReference<SwitchImage> mReference;

        MyHandler(SwitchImage msImage) {
            mReference = new WeakReference<SwitchImage>(msImage);
        }

        public void handleMessage(Message msg) {
            SwitchImage switchImage = mReference.get();
            if (switchImage == null) return;

            if (switchImage.moving && switchImage.viewList.size() > 0) {
                if (msg.what == switchImage.MOVING) {
                    switchImage.mPager.setCurrentItem(switchImage.mPager.getCurrentItem() + 1, true);
                    sendEmptyMessageDelayed(switchImage.MOVING, switchImage.timeGap);
                }
            }
        }
    }

    public SwitchImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray tArray = context.obtainStyledAttributes(attrs, R.styleable.page, defStyleAttr, 0);
        lastItemToNextOrStop = tArray.getBoolean(R.styleable.page_is_last_item_to_next_or_stop, true);

        tArray.recycle();

        LayoutInflater.from(context).inflate(R.layout.activity_guide, this, true);
        mPager = (ViewPager) findViewById(R.id.guide_viewpager);
        mDotsLayout = (LinearLayout) findViewById(R.id.guide_dots);
        mTextView = (TextView) findViewById(R.id.tv_banner_title_text);
//        mBtn = (ImageButton) findViewById(R.id.guide_btn);

        ViewPagerAdapter = new ViewPagerAdapter(viewList);

        mPager.setAdapter(ViewPagerAdapter);
        mPager.setOnPageChangeListener(new PageListener());
//        mBtn.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (btnJumpTOWhere != null)
//                    btnJumpTOWhere.jumpTO();
//            }
//        });
        handler = new MyHandler(this);
    }

    public SwitchImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchImage(Context context) {
        this(context, null);
    }

    @Deprecated
    public void setViewList(List<View> list) {
        viewList.clear();
        viewList.addAll(list);

        ViewPagerAdapter.notifyDataSetChanged();

        initDots(viewList.size());
    }

    private void initDots(int count) {
        mDotsLayout.removeAllViews();
        for (int j = 0; j < count; j++) {
            mDotsLayout.addView(initDot());
        }
        mDotsLayout.getChildAt(0).setSelected(true);
    }

    private View initDot() {
        return LayoutInflater.from(mContext).inflate(R.layout.layout_dot, null);
    }

    class PageListener implements OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            arg0 = (arg0 % mDotsLayout.getChildCount());
            //dot
            for (int i = 0; i < mDotsLayout.getChildCount(); i++) {
                if (i == arg0) {
                    mDotsLayout.getChildAt(i).setSelected(true);
                } else {
                    mDotsLayout.getChildAt(i).setSelected(false);
                }
            }
            mTextView.setText(texts[arg0]);

//            //button
//            if (arg0 == mDotsLayout.getChildCount() - 1) {
//                mBtn.setVisibility(View.VISIBLE);
//            } else {
//                mBtn.setVisibility(View.GONE);
//            }

            //message
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
                handler.sendEmptyMessageDelayed(MOVING, timeGap);
            }
        }
    }

    class ViewPagerAdapter extends PagerAdapter {
        private List<View> data;

        public ViewPagerAdapter(List<View> data) {
            super();
            this.data = data;
        }

        //update时舍弃所有View
        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            if (data.size() == 1) return 1;
            return lastItemToNextOrStop ? data.size() * LOOP_TIMES : data.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (data.get(position % data.size()).getParent() == null)
                container.addView(data.get(position % data.size()), 0);
            return data.get(position % data.size());
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    private View initView(int res) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_guide, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iguide_img);
        imageView.setImageResource(res);

        //hold imageView
        imageViewList.add(imageView);

        return view;
    }

    private View initView(int res, String url) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_guide, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iguide_img);
        //设置ImageView的超链接
        imageView.setTag(url);
        imageView.setImageResource(res);

        //hold imageView
        imageViewList.add(imageView);
        return view;
    }

    public void setMove(boolean flag) {
        setMove(flag, timeGap);
    }

    /**
     * 设置是否移动和移动时间间隔
     *
     * @param flag move or stop
     * @param time time gap of move
     */
    public void setMove(boolean flag, int time) {
        if (flag) {
            timeGap = time;
            moving = flag;
            //防止一张图的时候切换
            if (viewList != null && viewList.size() > 1)
                handler.sendEmptyMessageDelayed(MOVING, timeGap);
        } else {
            moving = flag;
            handler.removeCallbacksAndMessages(null);
        }
    }

    public void setJumpTo(BtnJumpTOWhere btnJumpTOWhere) {
        this.btnJumpTOWhere = btnJumpTOWhere;
    }

    public void initPager(int[] images) {
        viewList.clear();
        imageViewList.clear();

        for (int i = 0; i < images.length; i++) {
            View holdView = initView(images[i]);
            //设置序号，为了响应点击
            holdView.setTag(i);
            viewList.add(holdView);
        }
        initDots(viewList.size());
        mPager.setCurrentItem(viewList.size() * LOOP_TIMES / 2);

        if (images.length == 2) {
            //add one more time, size == 2 crash
            for (int i = 0; i < images.length; i++) {
                View holdView = initView(images[i]);
                holdView.setTag(i);
                viewList.add(holdView);
            }
        }

        ViewPagerAdapter.notifyDataSetChanged();
//        destroyDrawingCache();
    }

    public void initPager(int[] images, String[] urls, String[] texts) {
        if (images.length != urls.length || images.length != texts.length) throw new RuntimeException("确保长度相等");
        this.texts = texts.clone();
        viewList.clear();
        imageViewList.clear();

        for (int i = 0; i < images.length; i++) {
            View holdView = initView(images[i], urls[i]);
            //设置序号，为了响应点击
            holdView.setTag(i);
            viewList.add(holdView);
        }
        initDots(viewList.size());

        //当lastItemToNextOrStop为true,才设置初始很大的位置,否则会初始化显示最后一张
        if (lastItemToNextOrStop)
            mPager.setCurrentItem(viewList.size() * LOOP_TIMES / 2);

        if (images.length == 2) {
            //add one more time, size == 2 crash
            for (int i = 0; i < images.length; i++) {
                View holdView = initView(images[i], urls[i]);
                holdView.setTag(i);
                viewList.add(holdView);
            }
        }

        mTextView.setText(this.texts[0]);

        ViewPagerAdapter.notifyDataSetChanged();
        //        destroyDrawingCache();
    }

    //设置当前Viewpage位置
    public void setPagePosition(int p) {
        mPager.setCurrentItem(p);
    }

    public List<View> getViewList() {
        return viewList;
    }

    public void setOnClickListener(SwitchImageOnClick switchImageOnClick) {
        this.switchImageOnClick = switchImageOnClick;
        if (viewList.size() == 0) throw new RuntimeException("先调用initPager()");
        for (View view : viewList) {
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    SwitchImage.this.switchImageOnClick.viewClickedListener((Integer) (v.getTag()));
                }
            });
        }
    }
    //传个加载图片的接口进来
    public void setAndLoadImage(DisplayImageView displayImageView) {
        this.displayImageView = displayImageView;
        if (viewList.size() == 0) throw new RuntimeException("先调用initPager()");
        for (ImageView imageView : imageViewList) {
            displayImageView.displayImageFromURL(imageView, (String) imageView.getTag());
        }
    }

    public void onDestory() {
        if (handler != null) handler.removeCallbacksAndMessages(null);
    }

    //响应按钮点击
    public interface BtnJumpTOWhere {
        public void jumpTO();
    }

    //从URL加载图片
    public interface DisplayImageView {
        void displayImageFromURL(ImageView view, String url);
    }

    //响应ImageView点击
    public interface SwitchImageOnClick {
        void viewClickedListener(int position);
    }

}
