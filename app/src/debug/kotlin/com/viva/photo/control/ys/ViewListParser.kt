package com.viva.photo.control.ys

import com.viva.photo.control.BaseParser
import com.viva.photo.control.info.MenuInfo
import com.viva.photo.utils.LogUtils

class ViewListParser(url: String?): BaseParser(url) {

    private var hasNext: Boolean = false

    override fun parser(): ArrayList<Any>? {
        hasNext = false
        clear()
        connect()
        var childLinks = doc?.select("div.l_effect_top")?.select("div>a")?.select("img")
        var menuInfo = MenuInfo(null, childLinks?.attr("src"), null, null)
        if (!doc?.select("a.effect_img_right")?.hasClass("effect_img_right effect_img_righta")!!) {
            var nextUrl = doc?.select("a.effect_img_right")?.attr("href")
            if (nextUrl != null) {
                hasNext = true
                url = nextUrl
            }
        }
        var array = arrayListOf<Any>()
        array.add(menuInfo)
        return array
    }

    override fun hasNext(): Boolean {
        return hasNext
    }

}
