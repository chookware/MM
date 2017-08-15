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

    private var recylcerView: RecyclerView? = null
    var data: ArrayList<Any>? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        this.recylcerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView?) {
        this.recylcerView = null
    }

    override fun onBindViewHolder(holder: ViewCache?, position: Int) {
        if (data != null && data!!.size > position) {
            var catalogInfo = data!![position] as CatalogInfo
            holder?.title?.text = catalogInfo?.title
            holder?.extra?.text = catalogInfo?.extra
            if (recylcerView?.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
                Glide.with(holder?.itemView).load(catalogInfo?.image).apply(RequestOptions.centerCropTransform()).into(holder?.image)
            } else {
                Glide.with(holder?.itemView).load(catalogInfo?.image).apply(RequestOptions.centerCropTransform().onlyRetrieveFromCache(true)).into(holder?.image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewCache {
        var viewCache = ViewCache(View.inflate(parent?.context, R.layout.layout_catalog_item, null))
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
