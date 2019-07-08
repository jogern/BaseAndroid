package com.studyhelper.mvp_lib;

import java.lang.ref.WeakReference;

/**
 * Create on 2019/7/4.
 * @author jogern
 */
public class BaseViewPresenter<V extends BoardView, P extends Presenter> {

      private WeakReference<P> mPresenter;
      private WeakReference<V> mViewBoard;

      public V getViewBoard() {
            return mViewBoard.get();
      }

      public P getPresenter() {
            return mPresenter.get();
      }

      public void attachViewBoard(V viewBoard) {
            mViewBoard = new WeakReference<>(viewBoard);
      }

      public void attachPresenter(P presenter) {
            mPresenter = new WeakReference<>(presenter);
      }

      /**
       * 如果是Activity与Activity的onCreate关联<p/>
       * 如果是Fragment与Fragment的onAttach关联
       */
      public void onCreated() { }

      /**
       * 如果是Activity与Activity的onDestroy关联<p/>
       * 如果是Fragment与Fragment的onDetach关联
       */
      public void onDestroyed() { }

}
