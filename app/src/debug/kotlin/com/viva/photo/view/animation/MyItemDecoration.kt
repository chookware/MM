package com.viva.photo.view.animation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.graphics.drawable.Drawable
import android.view.View


class MyItemDecoration : RecyclerView.ItemDecoration {

    private val ATTRS = intArrayOf(android.R.attr.listDivider)
    private var mDivider: Drawable? = null

    constructor(context: Context) : super() {
        val array = context.obtainStyledAttributes(ATTRS)
        mDivider = array.getDrawable(0)
        array.recycle()
    }

    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        drawHorizontal(c, parent)
        drawVertical(c, parent)
    }

    // 水平线
    fun drawHorizontal(c: Canvas?, parent: RecyclerView?) {
        var childCount = parent!!.childCount
        if (childCount == 0) {
            return
        }
        // 在每一个子控件的底部画线
        for (i in 0..(childCount - 1)) {
            var child = parent.getChildAt(i)
            var left = child.left + child.paddingLeft
            var right = child.width + child.left - child.paddingRight
            var top = child.bottom - mDivider!!.intrinsicHeight - child.paddingBottom
            var bottom = top + mDivider!!.intrinsicHeight
            mDivider?.setBounds(left, top, right, bottom)
            mDivider?.draw(c)
        }
    }

    // 竖直线
    fun drawVertical(c: Canvas?, parent: RecyclerView?) {
        var childCount = parent!!.childCount// 在每一个子控件的右侧画线
        if (childCount == 0) {
            return
        }
        for (i in 0..(childCount - 1)) {
            var child = parent.getChildAt(i)
            var right = child.right - child.paddingRight
            var left = right - mDivider!!.intrinsicWidth
            var top = child.top + child.paddingTop
            var bottom = child.top + child.height - child.paddingBottom
            mDivider?.setBounds(left, top, right, bottom)
            mDivider?.draw(c)
        }
    }

    // Item之间的留白
    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        outRect!!.set(0, 0, mDivider!!.intrinsicWidth, mDivider!!.intrinsicHeight)
    }

}

