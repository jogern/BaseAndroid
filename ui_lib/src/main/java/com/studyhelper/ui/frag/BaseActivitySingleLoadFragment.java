package com.studyhelper.ui.frag;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.studyhelper.ui.BaseActivity;

/**
 * Create on 2019/7/8.
 * @author jogern
 */
public abstract class BaseActivitySingleLoadFragment extends BaseSingleLoadFragment {


      /** 增加Fragment到栈中 */
      protected void addFragmentInStack(Fragment fragment) {
            FragmentActivity activity = getActivity();
            if (activity instanceof BaseActivity){
                  ((BaseActivity) activity).addFragmentInStack(fragment);
            }
      }

      /** 增加Fragment到栈中,有动画效果 */
      protected void addFragmentInStack(int enter, int exit, int popEnter, int popExit, Fragment fragment) {
            FragmentActivity activity = getActivity();
            if (activity instanceof BaseActivity){
                 ((BaseActivity) activity).addFragmentInStack(enter, exit, popEnter, popExit, fragment);
            }
           // getBaseActivity().addFragmentInStack(enter, exit, popEnter, popExit, fragment);
      }

      /** 增加Fragment到容器中 */
      protected void addFragmentInContainer(Fragment fragment) {
            FragmentActivity activity = getActivity();
            if (activity instanceof BaseActivity){
                  ((BaseActivity) activity).addFragmentInContainer(fragment);
            }
            //getBaseActivity().addFragmentInContainer(fragment);
      }

      /**
       * 增加Fragment到容器中,有动画效果
       * <br/>
       * 请使用.setCustomAnimations(enter, exit, popEnter, popExit)，
       * 这个方法的第1个参数对应进栈动画，第4个参数对应出栈动画，<br/>
       * 所以是.setCustomAnimations(进栈动画, exit, popEnter, 出栈动画))
       */
      protected void addFragmentInContainer(int enter, int exit, int popEnter, int popExit, Fragment fragment) {
            FragmentActivity activity = getActivity();
            if (activity instanceof BaseActivity){
                  ((BaseActivity) activity).addFragmentInContainer(enter, exit, popEnter, popExit, fragment);
            }
//            getBaseActivity().addFragmentInContainer(enter, exit, popEnter, popExit, fragment);
      }

      /** 移除栈顶的Fragment */
      protected void removePopFragmentOfStack() {
            FragmentActivity activity = getActivity();
            if (activity instanceof BaseActivity){
                  ((BaseActivity) activity).removePopFragmentOfStack();
            }
//            getBaseActivity().removePopFragmentOfStack();
      }

}
