package com.duoduolicai360.stickydecorationdemo.lib;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

/**
 * Created by swg on 2017/8/30.
 */

public abstract class BaseDecoration extends RecyclerView.ItemDecoration {

    @ColorInt
    int mGroupBackground = Color.parseColor("#00000000");
    int mGroupHeight = 80;
    boolean isAlignLeft = true;
    @ColorInt
    int mDivideColor = Color.parseColor("#cccccc");
    int mDivideHeight = 0;

    Paint mDividePaint;

    public BaseDecoration(){
        mDividePaint = new Paint();
        mDividePaint.setColor(mDivideColor);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        String groupName = getGroupName(position);
        if (groupName == null)
            return;
        if (position == 0 || isFirstInGroup(position)){
            outRect.top = mGroupHeight;
        } else {
            outRect.top = mDivideHeight;
        }
    }

    /**
     * 判断是不是组中的第一个位置
     * 根据前一个组名，判断当前是否为新的组
     */
    private boolean isFirstInGroup(int position){
        if (position == 0){
            return true;
        } else {
            String preGroupName = getGroupName(position - 1);
            String groupName = getGroupName(position);
            return !TextUtils.equals(preGroupName, groupName);
        }
    }

    protected abstract String getGroupName(int position);

    void log(String msg) {
        Log.i("TAG", msg);
    }



















}
