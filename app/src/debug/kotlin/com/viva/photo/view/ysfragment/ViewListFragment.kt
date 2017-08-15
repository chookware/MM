package com.viva.photo.view.ysfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.viva.photo.R
import com.viva.photo.control.LoadHtml
import com.viva.photo.control.OnLoadListener
import com.viva.photo.control.ys.ViewListParser
import com.viva.photo.view.adapter.ViewListAdapter
import com.viva.photo.view.animation.MyItemDecoration

class ViewListFragment: Fragment() {

    private var parser: ViewListParser? = null
    private var loadHtml: LoadHtml? = null
    var recyclerView: RecyclerView? = null

    private var onLoadListener: OnLoadListener? = object : OnLoadListener {

        override fun onFinish() {
            if (parser?.hasNext() ?: false) {
                loadHtml?.load(parser, this)
            }
        }

        override fun onNext(any: Any?) {
            if (any != null && any is ArrayList<*>) {
                if (recyclerView?.adapter != null
                        && (recyclerView?.adapter as ViewListAdapter).data != null) {
                    (recyclerView?.adapter as ViewListAdapter).data?.addAll(any)
                    recyclerView?.adapter?.notifyDataSetChanged()
                } else {
                    var adapter = ViewListAdapter()
                    adapter.data = any as ArrayList<Any>
                    recyclerView?.adapter = adapter
                }
            }
        }

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.layout_fragment_view_list, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        recyclerView = view?.findViewById(R.id.layout_fragment_view_list_recyclerview) as RecyclerView
        initRecyclerView(recyclerView)
    }

    fun load(url: String?) {
        parser = ViewListParser(url)
        loadHtml = LoadHtml()
        loadHtml?.load(parser, onLoadListener)
    }

    private fun initRecyclerView(recyclerView: RecyclerView?) {
        recyclerView?.setHasFixedSize(true) // 设置固定大小
        initRecyclerLayoutManager(recyclerView) // 初始化布局
        initItemDecoration(recyclerView) // 初始化装饰
        initItemAnimator(recyclerView) // 初始化动画效果
        initScrollListener(recyclerView)
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

    private fun initScrollListener(recyclerView: RecyclerView?) {
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE ->
                        if (recyclerView != null && !recyclerView!!.canScrollVertically(1)) {
                            if (loadHtml?.isStop() ?: false) {
                                if (parser?.hasNext() ?: false) {
                                    loadHtml?.load(parser, onLoadListener)
                                }
                            }
                        }
                }
            }
        })
    }
}