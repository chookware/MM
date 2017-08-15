package com.viva.photo.view.ysfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chenenyu.router.annotation.InjectParam
import com.chenenyu.router.annotation.Route
import com.viva.photo.R
import com.viva.photo.control.BaseParser
import com.viva.photo.control.LoadHtml
import com.viva.photo.control.OnLoadListener
import com.viva.photo.control.ys.CatalogParser
import com.viva.photo.utils.LogUtils
import com.viva.photo.view.adapter.CatalogAdapter
import com.viva.photo.view.animation.MyItemDecoration


class CatalogFragment: Fragment() {

    private var loadHtml: LoadHtml? = null
    private var parser: BaseParser? = null
    var recyclerView: RecyclerView? = null

    private var onLoadListener = object : OnLoadListener {
        override fun onNext(any: Any?) {
            if (any != null && any is ArrayList<*>) {
                if (recyclerView?.adapter != null
                        && (recyclerView?.adapter as CatalogAdapter).data != null) {
                    (recyclerView?.adapter as CatalogAdapter).data?.addAll(any)
                    recyclerView?.adapter?.notifyDataSetChanged()
                } else {
                    var adapter = CatalogAdapter()
                    adapter.data = any as ArrayList<Any>
                    recyclerView?.adapter = adapter
                }
            }
        }

        override fun onFinish() {

        }

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.layout_fragment_catalog, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        recyclerView = view?.findViewById(R.id.layout_fragment_catalog_recyclerview) as RecyclerView
        initRecyclerView(recyclerView)
    }

    fun load(url: String?) {
        loadHtml = LoadHtml()
        parser = CatalogParser(url/*"http://pic.yesky.com/c/6_18332.shtml"*/)
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
