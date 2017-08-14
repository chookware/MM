package com.viva.photo.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.viva.photo.R
import com.viva.photo.control.info.CatalogInfo

class CatalogAdapter: RecyclerView.Adapter<CatalogAdapter.ViewCache>() {

    var data: ArrayList<Any>? = null

    override fun onBindViewHolder(holder: ViewCache?, position: Int) {
        if (data != null && data!!.size > position) {
            var catalogInfo = data!![position] as CatalogInfo
            holder?.title?.text = catalogInfo?.title
            holder?.extra?.text = catalogInfo?.extra
            Glide.with(holder?.itemView).load(holder?.image).apply(RequestOptions.centerCropTransform()).into(holder?.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewCache {
        var viewCache = ViewCache(View.inflate(parent?.context, R.layout.layout_catalog_item, null))
        return viewCache
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }


    class ViewCache: RecyclerView.ViewHolder {

        var image: ImageView? = null
        var title: TextView? = null
        var extra: TextView? = null

        constructor(itemView: View):super(itemView) {
            title = itemView.findViewById(R.id.layout_catalog_item_title) as TextView
            extra = itemView.findViewById(R.id.layout_catalog_item_extra) as TextView
            image = itemView.findViewById(R.id.layout_catalog_item_image) as ImageView
        }

    }
}
