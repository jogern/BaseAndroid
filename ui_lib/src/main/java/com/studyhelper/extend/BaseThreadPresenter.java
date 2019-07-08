package com.studyhelper.extend;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.studyhelper.mvp.BasePresenter;

/**
 * Create on 2019/3/27.
 * @author jogern
 */
public abstract class BaseThreadPresenter<D> extends BasePresenter<D> {

      private   HandlerThread mHandlerThread;
      protected Handler       mThreadHandler;

      private boolean isThreadQuit = true;

      @Override
      protected void onDestroy() {
            if (mHandlerThread != null) {
                  isThreadQuit = true;
                  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        mHandlerThread.quitSafely();
                  } else {
                        mHandlerThread.quit();
                  }
            }
      }

      @Override
      protected void onCreated() {
            String simpleName = this.getClass().getSimpleName();
            mHandlerThread = new HandlerThread(simpleName + "_thread");
            mHandlerThread.start();
            isThreadQuit = false;
            mThreadHandler = new Handler(mHandlerThread.getLooper()) {
                  @Override
                  public void handleMessage(Message msg) {
                        onHandleThreadMsg(msg.what, msg);
                  }
            };
      }

      public boolean isThreadQuit() {
            return isThreadQuit;
      }

      protected void removeAllMsg(int... whatArray) {
            if (whatArray == null) {
                  return;
            }
            for (int what : whatArray) {
                  mThreadHandler.removeMessages(what);
            }
      }

      /**
       * 处理线程消息
       * @param what 消息类型
       * @param msg  消息
       */
      protected abstract void onHandleThreadMsg(int what, Message msg);
}
