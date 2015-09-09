// Generated code from Butter Knife. Do not modify!
package com.szu.androidpractice.ui.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class HideToolbarOnScrollFragment$$ViewInjector<T extends com.szu.androidpractice.ui.fragments.HideToolbarOnScrollFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558464, "field 'recyclerView'");
    target.recyclerView = finder.castView(view, 2131558464, "field 'recyclerView'");
  }

  @Override public void reset(T target) {
    target.recyclerView = null;
  }
}
