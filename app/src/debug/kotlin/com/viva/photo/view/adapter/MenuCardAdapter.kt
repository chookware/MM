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

    companion object {
        private val TYPE_VIEW_1 = 0
        private val TYPE_VIEW_2 = 1
        private val TYPE_VIEW_3 = 2
        private val TYPE_VIEW_4 = 3
    }

    var data: MutableList<MutableList<MenuInfo>>? = null
    var parentFragment: Fragment? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewCache {
        var layoutId = getLayoutID(viewType)
        var viewCache = ViewCache(View.inflate(parent!!.context, layoutId, null))
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

        /*if (data != null) {
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
        }*/

        when (position) {
            TYPE_VIEW_1 -> {
                if (data?.get(0) != null) {
                    var array = data?.get(0) as MutableList<MenuInfo>
                    var image1 = holder?.itemView?.findViewById(R.id.layout_menu_card_type_1_image_1) as ImageView
                    var image2 = holder?.itemView?.findViewById(R.id.layout_menu_card_type_1_image_2) as ImageView
                    var image3 = holder?.itemView?.findViewById(R.id.layout_menu_card_type_1_image_3) as ImageView

                    Glide.with(holder?.itemView?.context).load(array?.get(0)?.image).into(image1)
                    Glide.with(holder?.itemView?.context).load(array?.get(1)?.image).into(image2)
                    Glide.with(holder?.itemView?.context).load(array?.get(2)?.image).into(image3)

                    var index = 0
                    var child = holder?.itemView?.findViewWithTag("text$index")
                    while (child != null) {
                        var text = child as TextView
                        text.text = array?.get(index + 2 + 1)?.title

                        index++
                        child = holder?.itemView?.findViewWithTag("text$index")
                    }
                }
            }
            TYPE_VIEW_2 -> {
                if (data?.get(1) != null) {
                    var array = data?.get(1) as MutableList<MenuInfo>
                    var image1 = holder?.itemView?.findViewById(R.id.layout_menu_card_type_2_image_1) as ImageView
                    var image2 = holder?.itemView?.findViewById(R.id.layout_menu_card_type_2_image_2) as ImageView

                    Glide.with(holder?.itemView?.context).load(array?.get(0)?.image).into(image1)
                    Glide.with(holder?.itemView?.context).load(array?.get(1)?.image).into(image2)

                    var index = 0
                    var child = holder?.itemView?.findViewWithTag("text$index")
                    while (child != null) {
                        var text = child as TextView
                        text.text = array?.get(index + 2 + 1)?.title

                        index++
                        child = holder?.itemView?.findViewWithTag("text$index")
                    }
                }
            }
            TYPE_VIEW_3 -> {
                if (data?.get(2) != null) {
                    var array = data?.get(2) as MutableList<MenuInfo>
                    var image1 = holder?.itemView?.findViewById(R.id.layout_menu_card_type_3_image_1) as ImageView
                    var image2 = holder?.itemView?.findViewById(R.id.layout_menu_card_type_3_image_2) as ImageView

                    Glide.with(holder?.itemView?.context).load(array?.get(0)?.image).into(image1)
                    Glide.with(holder?.itemView?.context).load(array?.get(1)?.image).into(image2)

                    var index = 0
                    var child = holder?.itemView?.findViewWithTag("text$index")
                    while (child != null) {
                        var text = child as TextView
                        text.text = array?.get(index + 2 + 1)?.title

                        index++
                        child = holder?.itemView?.findViewWithTag("text$index")
                    }
                }
            }
            TYPE_VIEW_4 -> {

            }
        }

    }

    override fun getItemCount(): Int {
        return data?.size ?: 4
    }

    override fun getItemViewType(position: Int): Int {
        var type = -1
        when (position) {
            TYPE_VIEW_1 -> type = TYPE_VIEW_1
            TYPE_VIEW_2 -> type = TYPE_VIEW_2
            TYPE_VIEW_3 -> type = TYPE_VIEW_3
            TYPE_VIEW_4 -> type = TYPE_VIEW_4
        }
        return type
    }

    fun getLayoutID(viewType: Int): Int {
        var layoutId = R.layout.layout_menu_card
        when (viewType) {
            TYPE_VIEW_1 -> layoutId = R.layout.layout_menu_card_type_1
            TYPE_VIEW_2 -> layoutId = R.layout.layout_menu_card_type_2
            TYPE_VIEW_3 -> layoutId = R.layout.layout_menu_card_type_3
            TYPE_VIEW_4 -> layoutId = R.layout.layout_menu_card_type_4
        }
        return layoutId
    }

    fun destroy() {
        parentFragment = null
    }

    class ViewCache: RecyclerView.ViewHolder {

//        var title: TextView? = null
//        var image: ImageView? = null
//        var recylerView: RecyclerView? = null

        constructor(itemView: View): super(itemView) {
//            title = itemView.findViewById(R.id.layout_menu_card_title) as TextView
//            image = itemView.findViewById(R.id.layout_menu_card_image) as ImageView
//            recylerView = itemView.findViewById(R.id.layout_menu_card_list) as RecyclerView
//
//            var layoutManager = LinearLayoutManager(itemView.context)
//            recylerView?.layoutManager = layoutManager
        }
    }
}


