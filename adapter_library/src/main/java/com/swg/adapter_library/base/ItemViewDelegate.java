package com.swg.adapter_library.base;

/**
 * Created by swg on 2017/12/5.
 */

public interface ItemViewDelegate<T> {

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);

}
