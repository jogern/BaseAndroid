package com.studyhelper.extend;

import com.studyhelper.recycler.BaseRecyclerItemAdapter;

import java.util.List;

/**
 * Create on 2019/3/27.
 * @author jogern
 */
public abstract class BaseListDataAdapter<T> extends BaseRecyclerItemAdapter {

      protected List<T> mDataList;

      protected void setListItems(List<T> listItems) {
            mDataList = listItems;
      }

      @Override
      public int getItemCount() {
            return mDataList == null ? 0 : mDataList.size();
      }

      public T getAdapterItem(int pos) {
            if (pos >= 0 && pos < getItemCount()) {
                  return mDataList.get(pos);
            }
            return null;
      }
}
