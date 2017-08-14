package com.viva.photo.view.ysfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager
import com.viva.photo.R
import com.viva.photo.control.LoadHtml
import com.viva.photo.control.OnLoadListener
import com.viva.photo.control.info.MenuInfo
import com.viva.photo.utils.LogUtils
import com.viva.photo.view.adapter.LayoutAdapter
import com.viva.photo.view.animation.MyItemDecoration


class YeskyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.layout_fragment_ys, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
//        initRecyclerView(view?.findViewById(R.id.layout_fragment_sy_recyclerview) as RecyclerView)
        var recyclerView = view?.findViewById(R.id.layout_fragment_sy_recyclerview) as RecyclerViewPager
        initViewPager(recyclerView)
        var loadHtml = LoadHtml()
        loadHtml.load(null, object : OnLoadListener {
            override fun onFinish(any: Any?) {
                if (any is ArrayList<*>) {
                    var array = ArrayList<Any>()
                    recyclerView.adapter = LayoutAdapter(activity, recyclerView, array)
                }
            }

        })
    }

    private fun initRecyclerView(recyclerView: RecyclerView?) {
        recyclerView?.setHasFixedSize(true) // 设置固定大小
        initRecyclerLayoutManager(recyclerView) // 初始化布局
        initItemDecoration(recyclerView) // 初始化装饰
        initItemAnimator(recyclerView) // 初始化动画效果
    }

    private fun initRecyclerLayoutManager(recyclerView: RecyclerView?) {
        // 错列网格布局
        recyclerView?.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    private fun initItemDecoration(recyclerView: RecyclerView?) {
        recyclerView?.addItemDecoration(MyItemDecoration(activity))
    }

    private fun initItemAnimator(recyclerView: RecyclerView?) {
        recyclerView?.itemAnimator = DefaultItemAnimator() // 默认动画
    }

    private fun initViewPager(mRecyclerView: RecyclerViewPager) {
        var layout = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,
                false)
        mRecyclerView.layoutManager = layout
//        mRecyclerView.adapter = LayoutAdapter(activity, mRecyclerView, null)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.isLongClickable = true
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, scrollState: Int) {
            }

            override fun onScrolled(recyclerView: RecyclerView?, i: Int, i2: Int) {
                val childCount = mRecyclerView.childCount
                val width = mRecyclerView.getChildAt(0).width
                val padding = (mRecyclerView.width - width) / 2

                for (j in 0..childCount - 1) {
                    val v = recyclerView!!.getChildAt(j)
                    //往左 从 padding 到 -(v.getWidth()-padding) 的过程中，由大到小
                    var rate = 0f
                    if (v.left <= padding) {
                        if (v.left >= padding - v.width) {
                            rate = (padding - v.left) * 1f / v.width
                        } else {
                            rate = 1f
                        }
                        v.scaleY = 1 - rate * 0.1f
                        v.scaleX = 1 - rate * 0.1f

                    } else {
                        //往右 从 padding 到 recyclerView.getWidth()-padding 的过程中，由大到小
                        if (v.left <= recyclerView.width - padding) {
                            rate = (recyclerView.width - padding - v.left) * 1f / v.width
                        }
                        v.scaleY = 0.9f + rate * 0.1f
                        v.scaleX = 0.9f + rate * 0.1f
                    }
                }
            }
        })
        mRecyclerView.addOnPageChangedListener({ oldPosition, newPosition -> Log.d("test", "oldPosition:$oldPosition newPosition:$newPosition") })

        mRecyclerView.addOnLayoutChangeListener({ v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            if (mRecyclerView.childCount < 3) {
                if (mRecyclerView.getChildAt(1) != null) {
                    if (mRecyclerView.currentPosition == 0) {
                        val v1 = mRecyclerView.getChildAt(1)
                        v1.scaleY = 0.9f
                        v1.scaleX = 0.9f
                    } else {
                        val v1 = mRecyclerView.getChildAt(0)
                        v1.scaleY = 0.9f
                        v1.scaleX = 0.9f
                    }
                }
            } else {
                if (mRecyclerView.getChildAt(0) != null) {
                    val v0 = mRecyclerView.getChildAt(0)
                    v0.scaleY = 0.9f
                    v0.scaleX = 0.9f
                }
                if (mRecyclerView.getChildAt(2) != null) {
                    val v2 = mRecyclerView.getChildAt(2)
                    v2.scaleY = 0.9f
                    v2.scaleX = 0.9f
                }
            }
        })
    }
}
