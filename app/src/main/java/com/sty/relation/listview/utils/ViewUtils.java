package com.sty.relation.listview.utils;

import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by tian on 2018/7/17.
 */

public class ViewUtils {

    /**
     * 两个listView同步滑动
     * @param listView1
     * @param listView2
     */
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
                        int top = subView.getTop();  //第一个可见item的顶部距离ListView顶部的距离，通常为负值
                        int top1 = listView1.getChildAt(0).getTop();
                        int position = view.getFirstVisiblePosition();
                        //Log.i("sty", "content top: " + top + " | title top:: " + top1 + " | position: " + position );

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
                    //Log.i("sty", "-----content top: " + top + " | title top:: " + top1 + " | firstVisibleItem: " + firstVisibleItem );

                    //如果两个首个显示的子view高度不等
                    if(!(top1 - 7 < top && top < top1 + 7)){
                        listView1.setSelectionFromTop(firstVisibleItem, top);
                        listView2.setSelectionFromTop(firstVisibleItem, top);
                    }
                    //使用以下代码可以解决两个listView同步不流畅的问题，但是应该会更耗性能
//                    if(top != top1){
//                        listView1.setSelectionFromTop(firstVisibleItem, top);
//                        listView2.setSelectionFromTop(firstVisibleItem, top);
//                    }
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
                    View otherView = listView2.getChildAt(0);
                    if(subView != null){
                        int top = subView.getTop();
                        int top1 = otherView.getTop();
                        int position = view.getFirstVisiblePosition();

                        //如果两个首个显示的子view高度不等
                        if(top != top1){
                            listView1.setSelectionFromTop(position, top);
                            listView2.setSelectionFromTop(position, top);
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                View subView = view.getChildAt(0);
                if(subView != null){
                    int top = subView.getTop();
                    listView1.setSelectionFromTop(firstVisibleItem, top);
                    listView2.setSelectionFromTop(firstVisibleItem, top);
                }
            }
        });
    }

}
