package com.studyhelper.mvp_lib.expansion;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.studyhelper.mvp_lib.BaseViewPresenter;
import com.studyhelper.mvp_lib.BoardView;
import com.studyhelper.mvp_lib.Presenter;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Create on 2019/7/8.
 * @author jogern
 */
public abstract class BaseHandlerThreadPresenter<V extends BoardView, P extends Presenter>
        extends BaseViewPresenter<V, P> {

      private AtomicBoolean mIsQuit = new AtomicBoolean(true);
      private HandlerThread mHandlerThread;
      private Handler       mWorkHandler;

      protected Handler getWorkHandler() {
            return mWorkHandler;
      }

      protected boolean isQuit() {
            return mIsQuit.get();
      }

      @Override
      public void onCreated() {
            super.onCreated();
            mHandlerThread = new HandlerThread(this.getClass().getSimpleName());
            mHandlerThread.start();
            mIsQuit.set(false);
            mWorkHandler = new Handler(mHandlerThread.getLooper()) {
                  @Override
                  public void handleMessage(Message msg) {
                        onHandleMessage(msg.what, msg);
                  }
            };
      }


      @Override
      public void onDestroyed() {
            super.onDestroyed();
            if (mHandlerThread != null) {
                  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        mHandlerThread.quitSafely();
                  } else {
                        mHandlerThread.quit();
                  }
                  mIsQuit.set(true);
            }
      }

      protected abstract void onHandleMessage(int what, Message msg);
}
