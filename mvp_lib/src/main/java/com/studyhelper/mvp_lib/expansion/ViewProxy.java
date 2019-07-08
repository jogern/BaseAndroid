package com.studyhelper.mvp_lib.expansion;

import android.app.Activity;
import android.view.View;

/**
 * Create on 2019/7/8.
 * @author jogern
 */
public interface ViewProxy {

      Activity getActivity();

      <T extends View> T findView(int resVId);

}
