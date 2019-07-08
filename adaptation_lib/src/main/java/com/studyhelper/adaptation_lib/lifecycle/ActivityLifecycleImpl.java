package com.studyhelper.adaptation_lib.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.studyhelper.adaptation_lib.strategy.AutoStrategy;
import com.studyhelper.adaptation_lib.strategy.DefaultStrategy;

/**
 * Create on 2019/6/10.
 * @author jogern
 */
public class ActivityLifecycleImpl implements Application.ActivityLifecycleCallbacks {

      private FragmentLifecycleImpl mFragmentLifecycle;
      private AutoStrategy          mAutoStrategy;

      public ActivityLifecycleImpl() {
            mAutoStrategy = new DefaultStrategy();
            mFragmentLifecycle = new FragmentLifecycleImpl(mAutoStrategy);
      }

      @Override
      public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            if (activity instanceof FragmentActivity) {
                  FragmentManager manager = ((FragmentActivity) activity).getSupportFragmentManager();
                  manager.registerFragmentLifecycleCallbacks(mFragmentLifecycle, true);
            }
            mAutoStrategy.startAdaptation(activity,activity);
      }

      @Override
      public void onActivityStarted(Activity activity) { }

      @Override
      public void onActivityResumed(Activity activity) { }

      @Override
      public void onActivityPaused(Activity activity) { }

      @Override
      public void onActivityStopped(Activity activity) { }

      @Override
      public void onActivitySaveInstanceState(Activity activity, Bundle outState) { }

      @Override
      public void onActivityDestroyed(Activity activity) {
            if (activity instanceof FragmentActivity) {
                  FragmentManager manager = ((FragmentActivity) activity).getSupportFragmentManager();
                  manager.unregisterFragmentLifecycleCallbacks(mFragmentLifecycle);
            }
      }
}
