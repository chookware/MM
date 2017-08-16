package com.viva.photo.view.ysfragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.viva.photo.R
import eu.fiskur.simpleviewpager.SimpleViewPager

class ViewPagerFragment: Fragment() {

    private var simpleViewPager: SimpleViewPager? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.layout_view_pager, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        simpleViewPager = view?.findViewById(R.id.layout_view_pager_contain) as SimpleViewPager
        initSimpeViewPager(simpleViewPager)

    }

    private fun initSimpeViewPager(simpleViewPager: SimpleViewPager?) {
        //optional:
        val indicatorColor = Color.parseColor("#ffffff")
        val selectedIndicatorColor = Color.parseColor("#fff000")
        simpleViewPager?.showIndicator(indicatorColor, selectedIndicatorColor)
    }

    fun load(index: Int?, urlArray: Array<String>?) {
        simpleViewPager?.setImageUrls(urlArray) { image, url -> Glide.with(activity).load(url).into(image) }
        simpleViewPager?.setCurrentItem(index!!)
    }

}
