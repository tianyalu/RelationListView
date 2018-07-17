package com.sty.relation.listview.utils;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by tian on 2018/7/17.
 */

public class ViewUtils {

    public static void setListViewOnTouchAndScrollListener(final ListView listView1, final ListView listView2){
        //设置listview2列表的scroll监听，用于滑动过程中左右不同步时校正
        listView2.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //如果停止滑动
                if(scrollState == 0 || scrollState == 1){
                    //获取第一个子view
                    View subView = view.getChildAt(0);
                    if(subView != null){
                        int top = subView.getTop();
                        int top1 = listView1.getChildAt(0).getTop();
                        int position = view.getFirstVisiblePosition();

                        //如果两个首个显示的子view高度不等
                        if(top != top1){
                            listView1.setSelectionFromTop(position, top);
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                View subView = view.getChildAt(0);
                View otherView = listView1.getChildAt(0);
                if(subView != null && otherView != null){
                    int top = subView.getTop();

                    int top1 = otherView.getTop();
                    //如果两个首个显示的子view高度不等
                    if(!(top1 - 7 < top && top < top1 + 7)){
                        listView1.setSelectionFromTop(firstVisibleItem, top);
                        listView2.setSelectionFromTop(firstVisibleItem, top);
                    }
                }
            }
        });

        //设置listview1列表的scroll监听，用于滑动过程中左右不同步时校正
        listView1.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //如果停止滑动
                if(scrollState == 0 || scrollState == 1){
                    //获取第一个子view
                    View subView = view.getChildAt(0);
                    if(subView != null){
                        int top = subView.getTop();
                        int top1 = listView2.getChildAt(0).getTop();
                        int position = view.getFirstVisiblePosition();

                        //如果两个首个显示的子view高度不等
                        if(top != top1){
                            listView2.setSelectionFromTop(position, top);
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                View subView = view.getChildAt(0);
                View otherView = listView2.getChildAt(0);
                if(subView != null && otherView != null){
                    int top = subView.getTop();

                    int top1 = otherView.getTop();
                    //如果两个首个显示的子view高度不等
                    if(!(top1 - 7 < top && top < top1 + 7)){
                        listView1.setSelectionFromTop(firstVisibleItem, top);
                        listView2.setSelectionFromTop(firstVisibleItem, top);
                    }
                }
            }
        });
    }

}
