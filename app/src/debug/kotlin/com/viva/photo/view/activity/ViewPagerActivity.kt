package com.viva.photo.view.activity

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.chenenyu.router.Router
import com.chenenyu.router.annotation.InjectParam
import com.chenenyu.router.annotation.Route
import com.viva.photo.R
import com.viva.photo.view.ysfragment.ViewPagerFragment

@Route("viewpager")
class ViewPagerActivity: FragmentActivity() {

    @InjectParam(key = "index")
    var index: Int? = null

    @InjectParam(key = "urlArray")
    var urlArray: Array<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_activity_view_pager)
        Router.injectParams(this)
        var viewPagerFragment = supportFragmentManager.findFragmentByTag("viewpager") as ViewPagerFragment
        viewPagerFragment.load(index, urlArray)

    }


}
