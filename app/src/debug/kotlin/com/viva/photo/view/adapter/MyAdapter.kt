package com.viva.photo.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.viva.photo.R

class MyAdapter: RecyclerView.Adapter<MyViewHolder> {

    private var mDataModels: ArrayList<DataModel>? = null
    private var mHeights: ArrayList<Int>? = null

    constructor(dataModels: ArrayList<DataModel>?) : super() {
        mDataModels = dataModels;
        mHeights = ArrayList()
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.layout_recylerview_sy_top_menu, parent, false);
        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        var dataModel = mDataModels?.get(position);

        // 随机高度, 模拟瀑布效果.
        if (mHeights != null && mHeights!!.size <= position) {
            mHeights!!.add((100 + Math.random() * 300) as Int)
        }

        var lp = holder?.getTvLabel()?.getLayoutParams()
        lp?.height = mHeights?.get(position);
    }

    override fun getItemCount(): Int {
        return mDataModels?.size ?: 0
    }

    fun addData(position: Int) {
        notifyItemInserted(position)
    }

    fun removeData(position: Int) {
        mDataModels?.removeAt(position)
        notifyItemRemoved(position)
    }
}

class MyViewHolder: RecyclerView.ViewHolder {

    private var mTvLabel: TextView? = null // 标签
    private var mTvDateTime: TextView? = null // 日期

    constructor(itemView: View?):super(itemView) {
    }

    fun getTvLabel(): TextView? {
        return mTvLabel
    }

    fun getTvDateTime(): TextView? {
        return mTvDateTime
    }

}
data class DataModel(var mLabel: String?, var mDateTime: String?)