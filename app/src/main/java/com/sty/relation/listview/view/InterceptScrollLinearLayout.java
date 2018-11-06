package com.sty.relation.listview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

/**
 * Created by tian on 2018/7/18.
 */

public class InterceptScrollLinearLayout extends LinearLayout {
    private static final int TOUCH_STATE_REST = 0;
    private static final int TOUCH_STATE_HORIZONTAL_SCROLLING = 1;
    private static final int TOUCH_STATE_VERTICAL_SCROLLING = -1;

    private int mTouchState = TOUCH_STATE_REST;
    private float mLastMotionX;
    private float mLastMotionY;
    private int mTouchSlop;

    public InterceptScrollLinearLayout(Context context) {
        super(context);
        init(context);
    }

    public InterceptScrollLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public InterceptScrollLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();
        //mTouchSlop = 5;
        Log.i("sty", "mTouchSlop: " + mTouchSlop);
    }

    //拦截onTouch事件  上下滑动时拦截事件返回true  左右滑动时不拦截返回false
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (mTouchState == TOUCH_STATE_HORIZONTAL_SCROLLING) {
                    intercept = false;  //左右滑动时不拦截事件
                } else if (mTouchState == TOUCH_STATE_VERTICAL_SCROLLING) {
                    intercept = true;  //上下滑动时拦截
                } else {
                    float x = ev.getX();
                    int xDiff = (int) Math.abs(x - mLastMotionX);
                    boolean xMoved = xDiff > mTouchSlop;

                    float y = ev.getY();
                    int yDiff = (int) Math.abs(y - mLastMotionY);
                    boolean yMoved = yDiff > mTouchSlop;

                    if(xMoved){
                        if(xDiff >= yDiff && xDiff >= mTouchSlop){ //Scroll if the user moved far enough along the X axis
                            mTouchState = TOUCH_STATE_HORIZONTAL_SCROLLING;
                            mLastMotionX = x;
                        }
                    }

                    if(yMoved){
                        if(yDiff > xDiff && yDiff >= mTouchSlop){ //Scroll if the user moved far enough along the Y axis
                            mTouchState = TOUCH_STATE_VERTICAL_SCROLLING;
                            mLastMotionY = y;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_DOWN:
                mTouchState = TOUCH_STATE_REST;
                mLastMotionX = ev.getX();
                mLastMotionY = ev.getY();
                intercept = false;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                //release the drag
                mTouchState = TOUCH_STATE_REST;
                intercept = false;
                break;
            default:
                break;

        }

        return intercept;
    }


}