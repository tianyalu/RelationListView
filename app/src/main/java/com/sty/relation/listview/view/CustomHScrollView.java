package com.sty.relation.listview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tian on 2018/7/18.
 */

public class CustomHScrollView extends HorizontalScrollView {
    ScrollViewObserver mScrollViewObserver = new ScrollViewObserver();

    public CustomHScrollView(Context context) {
        super(context);
    }

    public CustomHScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomHScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        //滚动时通知观察者
        if(mScrollViewObserver != null){
            mScrollViewObserver.notifyOnScrollChanged(l, t, oldl, oldt);
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }


    /**
     * 自定义的滚动监听接口
     * 当发生滚动事件时的接口，供外部访问
     */
    public interface OnScrollChangedListener{
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }

    //添加滚动事件监听
    public void addOnScrollChangedListener(OnScrollChangedListener listener){
        mScrollViewObserver.addOnScrollChangedListener(listener);
    }

    //移除滚动事件监听
    public void removeOnScrollChangedListener(OnScrollChangedListener listener){
        mScrollViewObserver.removeOnScrollChangedListener(listener);
    }

    /**
     * 滚动观察者
     */
    public static class ScrollViewObserver{
        List<OnScrollChangedListener> mChangedListeners;

        public ScrollViewObserver(){
            super();
            mChangedListeners = new ArrayList<>();
        }

        //添加滚动事件监听
        public void addOnScrollChangedListener(OnScrollChangedListener listener){
            mChangedListeners.add(listener);
        }

        //移除滚动事件监听
        public void removeOnScrollChangedListener(OnScrollChangedListener listener){
            mChangedListeners.remove(listener);
        }

        //通知
        public void notifyOnScrollChanged(int l, int t, int oldl, int oldt){
            if(mChangedListeners == null || mChangedListeners.size() == 0){
                return;
            }
            for(int i = 0; i < mChangedListeners.size(); i++){
                if(mChangedListeners.get(i) != null){
                    mChangedListeners.get(i).onScrollChanged(l, t, oldl, oldt);
                }
            }
        }
    }
}
