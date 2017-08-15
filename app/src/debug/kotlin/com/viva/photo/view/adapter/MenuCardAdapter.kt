package com.viva.photo.view.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chenenyu.router.Router
import com.viva.photo.R
import com.viva.photo.control.info.MenuInfo
import com.viva.photo.utils.LogUtils

class MenuCardAdapter: RecyclerView.Adapter<MenuCardAdapter.ViewCache>() {

    var data: MutableList<MenuInfo>? = null
    var parentFragment: Fragment? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewCache {
        var viewCache = ViewCache(View.inflate(parent!!.context, R.layout.layout_menu_card, null))
        var layoutParams = viewCache.itemView.layoutParams
        if (layoutParams == null) {
            layoutParams = ViewGroup.LayoutParams(parent.width, parent.height / 2)
        } else {
            layoutParams.width = parent.width
            layoutParams.height = parent.height / 2
        }
        viewCache.itemView.layoutParams = layoutParams
        return viewCache
    }

    override fun onBindViewHolder(holder: ViewCache?, position: Int) {

        if (data != null) {
            if (data!!.size > position) {
                var menuInfo = data!![position]
                holder?.title?.text = menuInfo.title
                Glide.with(holder?.itemView).load(menuInfo.image).apply(RequestOptions.centerCropTransform()).into(holder?.image)
                var adapter = ItemAdapter()
                adapter.data = menuInfo?.item
                holder?.recylerView?.adapter = adapter
                holder?.recylerView?.tag = menuInfo.url

                holder?.image?.setOnClickListener {
                    i ->
                    var url = holder?.recylerView?.tag as String
                    var bundle = Bundle()
                    bundle.putString("url", url)
                    Router.build("catalog").with(bundle).go(holder?.itemView?.context)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    fun destroy() {
        parentFragment = null
    }

    class ViewCache: RecyclerView.ViewHolder {

        var title: TextView? = null
        var image: ImageView? = null
        var recylerView: RecyclerView? = null

        constructor(itemView: View): super(itemView) {
            title = itemView.findViewById(R.id.layout_menu_card_title) as TextView
            image = itemView.findViewById(R.id.layout_menu_card_image) as ImageView
            recylerView = itemView.findViewById(R.id.layout_menu_card_list) as RecyclerView

            var layoutManager = LinearLayoutManager(itemView.context)
            recylerView?.layoutManager = layoutManager
        }
    }
}


