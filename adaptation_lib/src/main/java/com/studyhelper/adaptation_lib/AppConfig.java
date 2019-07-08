package com.studyhelper.adaptation_lib;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.studyhelper.adaptation_lib.lifecycle.ActivityLifecycleImpl;
import com.studyhelper.util_lib.Logcat;
import com.studyhelper.util_lib.ScreenUtil;


/**
 * Create on 2019/6/10.
 * @author jogern
 */
public class AppConfig {

      private static final class Holder {

            private static final AppConfig SIZE_CONFIG = new AppConfig();
      }

      public static AppConfig getInstance() {
            return Holder.SIZE_CONFIG;
      }

      /** 最初的 {@link DisplayMetrics} 的数据 */
      private DisplayInfo mInitDisplayInfo;
      /** 设备的屏幕总宽度, 单位 px */
      private int         mScreenWidth;
      /** 设备的屏幕总高度, 单位 px */
      private int         mScreenHeight;


      private AppConfig() {
            mActivityLifecycle = new ActivityLifecycleImpl();
      }

      private ActivityLifecycleImpl mActivityLifecycle;

      public void init(final Application application) {
            application.registerActivityLifecycleCallbacks(mActivityLifecycle);
            final DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
            mInitDisplayInfo = new DisplayInfo();
            mInitDisplayInfo.setDensity(displayMetrics.density);
            mInitDisplayInfo.setDensityDpi(displayMetrics.densityDpi);
            mInitDisplayInfo.setScaledDensity(displayMetrics.scaledDensity);
            mInitDisplayInfo.setXdpi(displayMetrics.xdpi);
            mInitDisplayInfo.setYdpi(displayMetrics.ydpi);

            int[] screenSize = ScreenUtil.getScreenSize(application);
            mScreenWidth = screenSize[0];
            mScreenHeight = screenSize[1]-ScreenUtil.getNavigationBarHeight(application);

            Logcat.d("mScreenWidth: "+mScreenWidth+" mScreenHeight: "+mScreenHeight);

            application.registerComponentCallbacks(new ComponentCallbacks() {
                  @Override
                  public void onConfigurationChanged(Configuration newConfig) {
                        int[] screenSize = ScreenUtil.getScreenSize(application);
                        mScreenWidth = screenSize[0];
                        mScreenHeight = screenSize[1]-ScreenUtil.getNavigationBarHeight(application);
                        Logcat.d("mScreenWidth: "+mScreenWidth+" mScreenHeight: "+mScreenHeight);
                  }

                  @Override
                  public void onLowMemory() { }
            });

      }

      public DisplayInfo getInitDisplayInfo() {
            return mInitDisplayInfo;
      }

      public int getScreenWidth() {
            return mScreenWidth;
      }

      public int getScreenHeight() {
            return mScreenHeight;
      }
}
