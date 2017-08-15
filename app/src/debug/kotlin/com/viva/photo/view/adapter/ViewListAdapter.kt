package com.viva.photo.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.viva.photo.R
import com.viva.photo.control.info.MenuInfo

class ViewListAdapter : RecyclerView.Adapter<ViewListAdapter.ViewCache>() {

    var data:MutableList<MenuInfo>? = null

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewCache {
        var itemView = LayoutInflater.from(parent?.context).inflate(R.layout.layout_list_item, null)
        var viewCache = ViewCache(itemView)
        return viewCache
    }

    override fun onBindViewHolder(holder: ViewCache?, position: Int) {
        if (data != null && data!!.size > position) {
            var menuInfo = data!![position]
            holder?.title?.tag = menuInfo.url
            holder?.title?.text = menuInfo.title
            holder?.title?.setOnClickListener {
                i ->
                var url = i.tag as String
            }
        }
    }

    class ViewCache: RecyclerView.ViewHolder {

        var title: TextView? = null

        constructor(itemView: View):super(itemView) {
            title = itemView.findViewById(R.id.layout_list_item_title) as TextView
        }

    }

}
