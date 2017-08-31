package com.viva.photo.view.adapter

import android.icu.util.Measure
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.Spanned
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
    var imageGroupHeight = 0

    private var onClickListener1 = View.OnClickListener {
        v ->
        if (v?.getTag(R.id.tag_first) is String) {
            var url = v?.getTag(R.id.tag_first) as String
            var bundle = Bundle()
            bundle.putString("url", url)
            Router.build("viewlist").with(bundle).go(v?.context)
        }
    }

    private var onClickListener2 = View.OnClickListener {
        v ->
        if (v?.getTag(R.id.tag_first) is String) {
            var url = v?.getTag(R.id.tag_first) as String
            var bundle = Bundle()
            bundle.putString("url", url)
            Router.build("catalog").with(bundle).go(v?.context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewCache {
        var layoutId = getLayoutID(viewType)
        var viewCache = ViewCache(View.inflate(parent!!.context, layoutId, null))

//        var text = viewCache?.itemView?.findViewWithTag("text1")
//        text?.measure(parent.width, parent.height)
//        var h = 0
//        if (text != null) {
//            h = text!!.measuredHeight * 10
//        }
//
//        if (viewCache?.itemView is CardView) {
//            var cardVew = viewCache?.itemView as CardView
//            h += cardVew.radius.toInt() * 2
//            h += cardVew.cardElevation.toInt() * 2
//        }

        var layoutParams = viewCache.itemView.layoutParams
        if (layoutParams == null) {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        imageGroupHeight = parent?.height / 4

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

        var layoutParams = holder?.itemView?.findViewWithTag("image")?.layoutParams
        if (layoutParams == null) {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        layoutParams?.height = imageGroupHeight

        holder?.itemView?.findViewWithTag("image")?.layoutParams = layoutParams

//        when (position) {
//            TYPE_VIEW_1 -> {
//                if (data?.get(0) != null) {
//                    var array = data?.get(0) as MutableList<MenuInfo>
//                    var image1 = holder?.itemView?.findViewById(R.id.layout_menu_card_type_1_image_1) as ImageView
//                    var image2 = holder?.itemView?.findViewById(R.id.layout_menu_card_type_1_image_2) as ImageView
//                    var image3 = holder?.itemView?.findViewById(R.id.layout_menu_card_type_1_image_3) as ImageView
//
//                    image1.setTag(R.id.tag_first, array?.get(0)?.url)
//                    image2.setTag(R.id.tag_first, array?.get(1)?.url)
//                    image3.setTag(R.id.tag_first, array?.get(2)?.url)
//
//                    image1.setOnClickListener(onClickListener1)
//                    image2.setOnClickListener(onClickListener1)
//                    image3.setOnClickListener(onClickListener1)
//
//                    Glide.with(holder?.itemView?.context).load(array?.get(0)?.image).apply(RequestOptions.centerCropTransform()).into(image1)
//                    Glide.with(holder?.itemView?.context).load(array?.get(1)?.image).apply(RequestOptions.centerCropTransform()).into(image2)
//                    Glide.with(holder?.itemView?.context).load(array?.get(2)?.image).apply(RequestOptions.centerCropTransform()).into(image3)
//
//                    var index = 1
//                    var child = holder?.itemView?.findViewWithTag("text$index")
//                    while (child != null) {
//                        var text = child as TextView
//                        text.text = underlineWithBold(array?.get(index + 2)?.title)
//
//                        text.setTag(R.id.tag_first, array?.get(index + 2)?.url)
//                        text.setOnClickListener(onClickListener1)
//
//                        index++
//                        child = holder?.itemView?.findViewWithTag("text$index")
//                    }
//                }
//            }
//            TYPE_VIEW_2 -> {
//                if (data?.get(1) != null) {
//                    var array = data?.get(1) as MutableList<MenuInfo>
//                    var image1 = holder?.itemView?.findViewById(R.id.layout_menu_card_type_2_image_1) as ImageView
//                    var image2 = holder?.itemView?.findViewById(R.id.layout_menu_card_type_2_image_2) as ImageView
//
//                    image1.setTag(R.id.tag_first, array?.get(0)?.url)
//                    image2.setTag(R.id.tag_first, array?.get(1)?.url)
//
//                    image1.setOnClickListener(onClickListener1)
//                    image2.setOnClickListener(onClickListener1)
//
//                    Glide.with(holder?.itemView?.context).load(array?.get(0)?.image).apply(RequestOptions.centerCropTransform()).into(image1)
//                    Glide.with(holder?.itemView?.context).load(array?.get(1)?.image).apply(RequestOptions.centerCropTransform()).into(image2)
//
//                    var index = 1
//                    var child = holder?.itemView?.findViewWithTag("text$index")
//                    while (child != null) {
//                        var text = child as TextView
//                        text.text = underlineWithBold(array?.get(index + 2)?.title)
//
//                        text.setTag(R.id.tag_first, array?.get(index + 2)?.url)
//                        text.setOnClickListener(onClickListener1)
//
//                        index++
//                        child = holder?.itemView?.findViewWithTag("text$index")
//                    }
//                }
//            }
//            TYPE_VIEW_3 -> {
//                if (data?.get(2) != null) {
//                    var array = data?.get(2) as MutableList<MenuInfo>
//                    var image1 = holder?.itemView?.findViewById(R.id.layout_menu_card_type_3_image_1) as ImageView
//                    var image2 = holder?.itemView?.findViewById(R.id.layout_menu_card_type_3_image_2) as ImageView
//
//                    image1.setTag(R.id.tag_first, array?.get(0)?.url)
//                    image2.setTag(R.id.tag_first, array?.get(1)?.url)
//
//                    image1.setOnClickListener(onClickListener1)
//                    image2.setOnClickListener(onClickListener1)
//
//                    Glide.with(holder?.itemView?.context).load(array?.get(0)?.image).apply(RequestOptions.centerCropTransform()).into(image1)
//                    Glide.with(holder?.itemView?.context).load(array?.get(1)?.image).apply(RequestOptions.centerCropTransform()).into(image2)
//
//                    var index = 1
//                    var child = holder?.itemView?.findViewWithTag("text$index")
//                    while (child != null) {
//                        var text = child as TextView
//                        text.text = underlineWithBold(array?.get(index + 2)?.title)
//
//                        text.setTag(R.id.tag_first, array?.get(index + 2)?.url)
//                        text.setOnClickListener(onClickListener1)
//
//                        index++
//                        child = holder?.itemView?.findViewWithTag("text$index")
//                    }
//                }
//            }
//            TYPE_VIEW_4 -> {
//                if (data?.get(3) != null) {
//                    var array = data?.get(3) as MutableList<MenuInfo>
//                    var label1 = holder?.itemView?.findViewWithTag("text1") as TextView
//                    var label2 = holder?.itemView?.findViewWithTag("text2") as TextView
//                    var label3 = holder?.itemView?.findViewWithTag("text3") as TextView
//                    var label4 = holder?.itemView?.findViewWithTag("text4") as TextView
//                    var label5 = holder?.itemView?.findViewWithTag("text5") as TextView
//                    var label6 = holder?.itemView?.findViewWithTag("text6") as TextView
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                        label1.text = Html.fromHtml("<u>" + array?.get(0)?.title + "</u>", 0)
//                    } else {
//                        label1.text = Html.fromHtml("<u>" + array?.get(0)?.title + "</u>")
//
//                    }
//                    label1.setTag(R.id.tag_first, array?.get(0)?.url)
//                    label1.setOnClickListener(onClickListener2)
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                        label2.text = Html.fromHtml("<u>" + array?.get(1)?.title + "</u>", 0)
//                    } else {
//                        label2.text = Html.fromHtml("<u>" + array?.get(1)?.title + "</u>")
//
//                    }
//                    label2.setTag(R.id.tag_first, array?.get(1)?.url)
//                    label2.setOnClickListener(onClickListener2)
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                        label3.text = Html.fromHtml("<u>" + array?.get(2)?.title + "</u>", 0)
//                    } else {
//                        label3.text = Html.fromHtml("<u>" + array?.get(2)?.title + "</u>")
//
//                    }
//                    label3.setTag(R.id.tag_first, array?.get(2)?.url)
//                    label3.setOnClickListener(onClickListener2)
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                        label4.text = Html.fromHtml("<u>" + array?.get(3)?.title + "</u>", 0)
//                    } else {
//                        label4.text = Html.fromHtml("<u>" + array?.get(3)?.title + "</u>")
//
//                    }
//                    label4.setTag(R.id.tag_first, array?.get(3)?.url)
//                    label4.setOnClickListener(onClickListener2)
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                        label5.text = Html.fromHtml("<u>" + array?.get(4)?.title + "</u>", 0)
//                    } else {
//                        label5.text = Html.fromHtml("<u>" + array?.get(4)?.title + "</u>")
//
//                    }
//                    label5.setTag(R.id.tag_first, array?.get(4)?.url)
//                    label5.setOnClickListener(onClickListener2)
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                        label6.text = Html.fromHtml("<u>" + array?.get(5)?.title + "</u>", 0)
//                    } else {
//                        label6.text = Html.fromHtml("<u>" + array?.get(5)?.title + "</u>")
//
//                    }
//                    label6.setTag(R.id.tag_first, array?.get(5)?.url)
//                    label6.setOnClickListener(onClickListener2)
//                }
//            }
//        }

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

    fun underlineWithBold(title: String?): Spanned {
        var result: Spanned
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = Html.fromHtml("<u><i>$title</i></u>", 0)
        } else {
            result = Html.fromHtml("<u><i>$title</i></u>")
        }
        return result
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


