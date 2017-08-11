package com.viva.photo.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.viva.photo.R
import com.viva.photo.control.info.MenuInfo
import com.viva.photo.utils.LogUtils
import java.util.ArrayList

class LayoutAdapter: RecyclerView.Adapter<SimpleViewHolder> {
    companion object {
        private val DEFAULT_ITEM_COUNT = 0
    }

    private var mContext: Context? = null
    private var mRecyclerView: RecyclerView? = null
    private var mItems: MutableList<Any>? = null
    private var mCurrentItemId = 0

    constructor(context: Context, recyclerView: RecyclerView,  mItems: MutableList<Any>?):super() {
        mContext = context
        this.mItems = mItems
//        for (i in 0..itemCount - 1) {
//            addItem(i)
//        }

        mRecyclerView = recyclerView
    }

    fun addItem(position: Int) {
        val id = mCurrentItemId++
        mItems?.add(position, id)
        notifyItemInserted(position)
    }

    fun removeItem(position: Int) {
        mItems?.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_recylerview_sy_top_menu_item, parent, false)
        return SimpleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
//        holder.title?.text = mItems?.get(position).toString()
        if (mItems!!.size > position) {
            if ( mItems?.get(position) is MenuInfo) {
                var menuInfo = mItems?.get(position) as MenuInfo
                Glide.with(mRecyclerView).load(menuInfo.image).into(holder.title)
                LogUtils.v("sgc load " + menuInfo.image)
            }
        }
        val itemView = holder.itemView
        itemView.setOnClickListener { Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show() }
        val itemId = mItems?.get(position)
    }

    override fun getItemCount(): Int {
        return mItems?.size ?: 0
    }
}

class SimpleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var title: ImageView? = null

    init {
        title = view.findViewById(R.id.layout_recylerview_sy_top_menu_image) as ImageView
    }
}
