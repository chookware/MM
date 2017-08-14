package com.viva.photo.view.activity

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.chenenyu.router.Router
import com.chenenyu.router.annotation.InjectParam
import com.chenenyu.router.annotation.Route
import com.viva.photo.R
import com.viva.photo.view.ysfragment.CatalogFragment

@Route("catalog")
class CatalogActivity: FragmentActivity() {

    @InjectParam(key = "url")
    var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_catalog)
        Router.injectParams(this) // 实现参数注入
        var catalogFragment = supportFragmentManager.findFragmentByTag("catalog") as CatalogFragment
        catalogFragment?.load(url)
    }

}
