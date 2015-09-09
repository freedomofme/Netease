// Generated code from Butter Knife. Do not modify!
package com.szu.androidpractice.ui.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class SlideMenuFragment$$ViewInjector<T extends com.szu.androidpractice.ui.fragments.SlideMenuFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558466, "field 'mDrawerLayout'");
    target.mDrawerLayout = finder.castView(view, 2131558466, "field 'mDrawerLayout'");
    view = finder.findRequiredView(source, 2131558468, "field 'mDrawerMenuListView'");
    target.mDrawerMenuListView = finder.castView(view, 2131558468, "field 'mDrawerMenuListView'");
  }

  @Override public void reset(T target) {
    target.mDrawerLayout = null;
    target.mDrawerMenuListView = null;
  }
}
