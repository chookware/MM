package com.viva.photo.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.viva.photo.R
import com.viva.photo.control.info.MenuInfo

class MenuCardAdapter(): RecyclerView.Adapter<MenuCardAdapter.ViewCache>() {

    var data: MutableList<MenuInfo>? = null

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
//                holder?.title?.text = menuInfo.title
            }
        }

    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    class ViewCache: RecyclerView.ViewHolder {

        var title: TextView? = null
        var image: ImageView? = null

        constructor(itemView: View): super(itemView) {
            title = itemView.findViewById(R.id.layout_menu_card_title) as TextView
            image = itemView.findViewById(R.id.layout_menu_card_image) as ImageView
        }
    }
}


