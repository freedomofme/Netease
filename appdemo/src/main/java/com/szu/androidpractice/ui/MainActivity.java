package com.szu.androidpractice.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.szu.androidpractice.R;
import com.szu.androidpractice.adapter.MainAdapter;
import com.szu.androidpractice.adapter.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lgp on 2015/4/6.
 */
public class MainActivity extends BaseActivity implements RecyclerItemClickListener.OnItemClickListener {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;

    public static final String ITEM_CLICK_KEY = "ITEM_CLICK_KEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initToolbar();
        initRecyclerView();
    }

    private void initToolbar(){
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(getColor(R.color.action_bar_title_color));

        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.collapseActionView();
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle the menu item
                switch (item.getItemId()) {
                    case R.id.setting:
                        return true;
                    case R.id.exit:
                        MainActivity.this.finish();
                        return true;
                    case android.R.id.home:
                        return true;
                    default:
                        return false;
                }
            }
        });
        //setSupportActionBar(toolbar);
    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainAdapter recyclerAdapter = new MainAdapter(createItemList());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, this));
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, SampleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(ITEM_CLICK_KEY, position);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    private List<String> createItemList() {
        List<String> itemList = new ArrayList<>();
        itemList.add(getString(R.string.hide_toolbar_onscroll));
        itemList.add(getString(R.string.slide_menu));
        return itemList;
    }
}
