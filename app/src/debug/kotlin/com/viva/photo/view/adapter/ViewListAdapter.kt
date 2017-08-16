package com.viva.photo.view.adapter

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chenenyu.router.Router
import com.viva.photo.R
import com.viva.photo.control.info.MenuInfo
import com.viva.photo.utils.LogUtils

class ViewListAdapter : RecyclerView.Adapter<ViewListAdapter.ViewCache>() {

    var data:ArrayList<Any>? = null

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewCache {
        var itemView = View.inflate(parent?.context, R.layout.layout_list_view, null)
        var viewCache = ViewCache(itemView)
        var layoutParams = viewCache.itemView.layoutParams
        if (layoutParams == null) {
            layoutParams = ViewGroup.LayoutParams(parent!!.width / 2, parent!!.height / 3)
        } else {
            layoutParams.width = parent!!.width / 2
            layoutParams.height = parent!!.height / 3
        }
        viewCache.itemView.layoutParams = layoutParams
        return viewCache
    }

    override fun onBindViewHolder(holder: ViewCache?, position: Int) {
        if (data != null && data!!.size > position) {
            var menuInfo = data!![position] as MenuInfo
//            holder?.itemView?.tag = menuInfo.url
//            holder?.title?.text = menuInfo.title
            Glide.with(holder?.itemView).load(menuInfo?.url).apply(RequestOptions.centerCropTransform()).into(holder?.image)
            holder?.itemView?.setOnClickListener {
                i ->
//                var url = i.tag as String
                var urlArray = arrayOfNulls<String>(data!!.size)
                data?.forEachIndexed { index, any ->
                    var info = any as MenuInfo
                    urlArray[index] = info.url
                }
                var bundle = Bundle()
                bundle.putInt("index", position)
                bundle.putStringArray("urlArray", urlArray)
                Router.build("viewpager").with(bundle).go(holder?.itemView?.context)
            }
        }
    }

    class ViewCache: RecyclerView.ViewHolder {

        var image: ImageView? = null

        constructor(itemView: View):super(itemView) {
            image = itemView.findViewById(R.id.layout_list_view_image) as ImageView
        }

    }

}
