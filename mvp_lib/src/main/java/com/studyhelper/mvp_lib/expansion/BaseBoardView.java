package com.studyhelper.mvp_lib.expansion;

import android.app.Activity;
import android.view.View;

/**
 * Create on 2019/7/8.
 * @author jogern
 */
public abstract class BaseBoardView<VP extends ViewProxy> {

      private VP mViewProxy;

      public void attachProxy(VP viewProxy) {
            mViewProxy = viewProxy;
      }

      protected VP getViewProxy() {
            return mViewProxy;
      }

      protected Activity getActivity(){
            if (mViewProxy != null) {
                  return mViewProxy.getActivity();
            }
            return null;
      }

      protected <V extends View> V findView(int resVId) {
            if (mViewProxy != null) {
                  return mViewProxy.findView(resVId);
            }
            return null;
      }


}
