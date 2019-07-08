package com.studyhelper.mvp_lib.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.studyhelper.mvp_lib.BaseViewPresenter;
import com.studyhelper.mvp_lib.BoardView;
import com.studyhelper.mvp_lib.Presenter;
import com.studyhelper.ui.frag.BaseActivitySingleLoadFragment;

/**
 * Create on 2019/7/5.
 * @author jogern
 */
public abstract class BaseMvpSingleLoadFragment<V extends BoardView, P extends Presenter> extends
        BaseActivitySingleLoadFragment {

      private BaseViewPresenter<V,P> mPresenter;

      protected P getPresenter() {
            if (mPresenter != null) {
                  return mPresenter.getPresenter();
            }
            return null;
      }

      protected V getViewBoard() {
            if (mPresenter != null) {
                  return mPresenter.getViewBoard();
            }
            return null;
      }

      @Override
      public void onAttach(Context context) {
            super.onAttach(context);
            mPresenter = createPresenter();
            if (mPresenter == null) {
                  throw new RuntimeException("must create Presenter");
            }
            mPresenter.onCreated();
      }

      @Override
      public void onDetach() {
            super.onDetach();
            mPresenter.onDestroyed();
      }

      protected abstract BaseViewPresenter<V,P> createPresenter();

}
