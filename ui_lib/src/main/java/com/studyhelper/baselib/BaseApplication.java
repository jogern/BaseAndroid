package com.studyhelper.baselib;

import android.app.Application;
import android.content.Context;

import com.coder.zzq.smartshow.core.SmartShow;

/**
 * Create on 2019/3/18.
 * @author jogern
 */
public class BaseApplication extends Application {

      private SaveCrashHandler mSaveCrashHandler = new SaveCrashHandler();

      @Override
      protected void attachBaseContext(Context base) {
            super.attachBaseContext(base);
            mSaveCrashHandler.init(this);
      }

      @Override
      public void onCreate() {
            SmartShow.init(this);
            super.onCreate();
      }
}
