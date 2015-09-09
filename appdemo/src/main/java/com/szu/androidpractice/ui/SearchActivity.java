package com.szu.androidpractice.ui;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.szu.androidpractice.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lgp on 2015/4/9.
 */
public class SearchActivity extends BaseActivity{
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.inject(this);
        initToolbar();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("onNewIntent", intent.getAction());
        if (Intent.ACTION_SEARCH.equals(intent.getAction())){
            handleQuery(intent.getStringExtra(SearchManager.QUERY));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.expandActionView();
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        ComponentName componentName = getComponentName();

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    private void initToolbar(){
        toolbar.setTitle("");
        toolbar.setTitleTextColor(getColor(R.color.action_bar_title_color));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void handleQuery(String query){
        Log.d("handleQuery", query);
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }
}
