package com.viva.photo.view.adapter

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.chenenyu.router.Router
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
            holder?.itemView?.tag = catalogInfo?.url
            holder?.title?.text = catalogInfo?.title
            holder?.extra?.text = catalogInfo?.extra

            holder?.text?.visibility = View.VISIBLE
            holder?.text?.setText(R.string.title_to_loading)
            Glide.with(holder?.itemView).load(catalogInfo?.image).apply(RequestOptions.centerCropTransform()).listener(object : RequestListener<Drawable>{
                override fun onResourceReady(p0: Drawable?, p1: Any?, p2: Target<Drawable>?, p3: DataSource?, p4: Boolean): Boolean {
                    holder?.text?.visibility = View.INVISIBLE
                    return false
                }

                override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: Target<Drawable>?, p3: Boolean): Boolean {
                    holder?.text?.setText(R.string.title_to_load_fail)
                    return false
                }

            }).into(holder?.image)
            holder?.itemView?.setOnClickListener {
                i ->
                var url = holder?.itemView?.tag as String
                var bundle = Bundle()
                bundle.putString("url", url)
                Router.build("viewlist").with(bundle).go(holder?.itemView?.context)
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
        var text: TextView? = null

        constructor(itemView: View):super(itemView) {
            title = itemView.findViewById(R.id.layout_catalog_item_title) as TextView
            extra = itemView.findViewById(R.id.layout_catalog_item_extra) as TextView
            image = itemView.findViewById(R.id.layout_catalog_item_image) as ImageView
            text = itemView.findViewById(R.id.layout_catalog_item_text) as TextView
        }

    }
}
