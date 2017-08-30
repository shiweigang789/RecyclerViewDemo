package com.duoduolicai360.stickydecorationdemo.lib;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.duoduolicai360.stickydecorationdemo.lib.listener.PowerGroupListener;

/**
 * Created by Administrator on 2017/8/30.
 */

public class PowerfulStickyDecoration extends BaseDecoration {

    private PowerGroupListener mGroupListener;
    private Paint mGroupPaint;

    private PowerfulStickyDecoration(PowerGroupListener listener){
        super();
        this.mGroupListener = listener;
        mGroupPaint = new Paint();
        mGroupPaint.setColor(mGroupBackground);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int itemCount = state.getItemCount();
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        String preGroupName;
        String currentGroupName = null;
        for (int i = 0; i < childCount; i++){
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            preGroupName = currentGroupName;
            currentGroupName = getGroupName(position);
            if (currentGroupName == null || TextUtils.equals(currentGroupName, preGroupName)){
                if (mDivideHeight != 0){
                    int bottom = view.getTop();
                    if (bottom < mGroupHeight){
                        continue;
                    }
                    c.drawRect(left, bottom - mDivideHeight, right, bottom, mDividePaint);
                }
            } else {
                int viewBottom =  view.getBottom();
                int top = Math.max(mGroupHeight, view.getTop());
                if (position + 1 < itemCount){
                    String nextGroupName = getGroupName(position + 1);
                    if (!currentGroupName.equals(nextGroupName) && viewBottom < top){
                        top = viewBottom;
                    }
                }
                c.drawRect(left, top - mGroupHeight, right, top, mGroupPaint);
                View groupView = getGroupView(position);
                if (groupView == null)
                    return;
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(right, mGroupHeight);
                groupView.setLayoutParams(layoutParams);
                groupView.setDrawingCacheEnabled(true);
                groupView.measure(
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                groupView.layout(0, 0, right, mGroupHeight);
                groupView.buildDrawingCache();
                l("groupView.getWidth() after: " + groupView.getWidth());
                Bitmap bitmap = groupView.getDrawingCache();
                int marginLeft = isAlignLeft ? 0 : right - groupView.getMeasuredWidth();
                c.drawBitmap(bitmap, left + marginLeft, top - mGroupHeight, null);
            }
        }

    }

    @Override
    protected String getGroupName(int position) {
        if (mGroupListener != null) {
            return mGroupListener.getGroupName(position);
        } else {
            return null;
        }
    }

    /**
     * 获取组View
     *
     * @param position position
     * @return 组名
     */
    private View getGroupView(int position) {
        if (mGroupListener != null) {
            return mGroupListener.getGroupView(position);
        } else {
            return null;
        }
    }

    public static class Builder {

        PowerfulStickyDecoration mDecoration;

        private Builder(PowerGroupListener listener){
            mDecoration = new PowerfulStickyDecoration(listener);
        }

        public static Builder init(PowerGroupListener listener){
            return new Builder(listener);
        }

        public Builder setGroupHeight(int groupHeight){
            mDecoration.mGroupHeight = groupHeight;
            return this;
        }

        public Builder setGroupBackground(@ColorInt int background){
            mDecoration.mGroupBackground = background;
            mDecoration.mGroupPaint.setColor(mDecoration.mGroupBackground);
            return this;
        }

        public Builder isAlignLeft(boolean b){
            mDecoration.isAlignLeft = b;
            return this;
        }

        /**
         * 设置分割线高度
         *
         * @param height 高度
         * @return this
         */
        public Builder setDivideHeight(int height) {
            mDecoration.mDivideHeight = height;
            return this;
        }

        /**
         * 设置分割线颜色
         *
         * @param color color
         * @return this
         */
        public Builder setDivideColor(@ColorInt int color) {
            mDecoration.mDivideColor = color;
            return this;
        }

        public PowerfulStickyDecoration build() {
            return mDecoration;
        }

    }

    private void l(String message) {
        Log.i("TAG", message);
    }

}
