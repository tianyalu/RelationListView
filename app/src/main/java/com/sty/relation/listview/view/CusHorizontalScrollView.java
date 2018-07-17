package com.sty.relation.listview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by tian on 2018/7/17.
 */

public class CusHorizontalScrollView extends HorizontalScrollView {
    private HorizontalScrollView mView;

    public CusHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(mView != null){
            mView.scrollTo(l, t);
        }
    }

    public void setSynHorizontalScrollView(HorizontalScrollView horizontalScrollView){
        mView = horizontalScrollView;
    }
}
