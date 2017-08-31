package com.viva.photo.view.ysfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.viva.photo.R
import com.viva.photo.control.LoadHtml
import com.viva.photo.control.OnLoadListener
import com.viva.photo.control.info.MenuInfo
import com.viva.photo.control.ys.MainParser
import com.viva.photo.utils.LogUtils
import com.viva.photo.view.adapter.MenuCardAdapter

class MainFragment(): Fragment() {

    private var menuAdapter: MenuCardAdapter? = null
    private var loadingView: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.layout_fragment_ys, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        var recyclerView = view?.findViewById(R.id.layout_fragment_sy_recyclerview) as RecyclerView
        initRecyclerView(recyclerView)
        loadingView = view?.findViewById(R.id.cool_wait_view)
        recyclerView?.visibility = View.INVISIBLE
    }

    private fun initRecyclerView(recyclerView: RecyclerView?) {
        var layout = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,
                false)
        recyclerView?.layoutManager = layout

        menuAdapter = MenuCardAdapter()
        menuAdapter?.parentFragment = this
        recyclerView?.adapter = menuAdapter

        var loadHtml = LoadHtml()
        loadHtml.load(MainParser(), object : OnLoadListener {
            override fun onNext(any: Any?) {
                if (any is MutableList<*>) {
                    loadingView?.visibility = View.INVISIBLE
                    recyclerView?.visibility = View.VISIBLE
                    menuAdapter?.data = any as MutableList<MutableList<MenuInfo>>
                    menuAdapter?.notifyDataSetChanged()
                }
             }

            override fun onFinish() {

            }

        })
    }

    override fun onDestroy() {
        menuAdapter?.destroy()
        super.onDestroy()
    }

}
