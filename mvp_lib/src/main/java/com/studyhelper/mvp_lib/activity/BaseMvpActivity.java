package com.studyhelper.mvp_lib.activity;

import android.os.Bundle;

import com.studyhelper.mvp_lib.BaseViewPresenter;
import com.studyhelper.mvp_lib.BoardView;
import com.studyhelper.mvp_lib.Presenter;
import com.studyhelper.ui.BaseActivity;

/**
 * Create on 2019/7/4.
 * @author jogern
 */
public abstract class BaseMvpActivity<V extends BoardView, P extends Presenter> extends BaseActivity {

      private BaseViewPresenter<V, P> mPresenter;

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
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mPresenter = createPresenter();
            mPresenter.onCreated();
      }


      @Override
      protected void onDestroy() {
            super.onDestroy();
            mPresenter.onDestroyed();
            mPresenter = null;
      }

      protected abstract BaseViewPresenter<V, P> createPresenter();

}
