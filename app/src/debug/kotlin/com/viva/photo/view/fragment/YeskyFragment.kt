package com.viva.photo.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.viva.photo.R

class YeskyFragment(): Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.layout_fragment_ys, null)
//        return null
    }

    private fun initRecyclerView(recyclerView: RecyclerView?) {
        recyclerView?.setHasFixedSize(true) // 设置固定大小
        initRecyclerLayoutManager(recyclerView); // 初始化布局
        initRecyclerAdapter(recyclerView); // 初始化适配器
//        initItemDecoration(recyclerView); // 初始化装饰
//        initItemAnimator(recyclerView); // 初始化动画效果
    }

    private fun initRecyclerLayoutManager(recyclerView: RecyclerView?) {
        // 错列网格布局
        recyclerView?.layoutManager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
    }

    private fun initRecyclerAdapter(recyclerView: RecyclerView?) {
//        mAdapter = new MyAdapter(getData());
//        recyclerView.setAdapter(mAdapter);
    }

}
