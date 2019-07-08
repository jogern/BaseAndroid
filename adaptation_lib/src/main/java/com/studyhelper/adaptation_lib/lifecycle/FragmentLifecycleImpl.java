package com.studyhelper.adaptation_lib.lifecycle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.studyhelper.adaptation_lib.strategy.AutoStrategy;

/**
 * Create on 2019/6/10.
 * @author jogern
 */
 class FragmentLifecycleImpl extends FragmentManager.FragmentLifecycleCallbacks {

       private AutoStrategy mAutoStrategy;

       FragmentLifecycleImpl(AutoStrategy autoStrategy) {
            mAutoStrategy = autoStrategy;
      }

      @Override
      public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
           mAutoStrategy.startAdaptation(f.getActivity(),f);
      }

      @Override
      public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
            super.onFragmentDestroyed(fm, f);
      }
}
