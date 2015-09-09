// Generated code from Butter Knife. Do not modify!
package com.szu.androidpractice.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class MainActivity$$ViewInjector<T extends com.szu.androidpractice.ui.MainActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558463, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131558463, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131558464, "field 'recyclerView'");
    target.recyclerView = finder.castView(view, 2131558464, "field 'recyclerView'");
  }

  @Override public void reset(T target) {
    target.toolbar = null;
    target.recyclerView = null;
  }
}
