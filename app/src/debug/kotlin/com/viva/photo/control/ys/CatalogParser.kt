package com.viva.photo.control.ys

import com.viva.photo.control.BaseParser
import com.viva.photo.control.info.CatalogInfo
import com.viva.photo.control.info.MenuInfo
import com.viva.photo.utils.LogUtils

class CatalogParser(url: String?): BaseParser(url) {

    override fun parser(): ArrayList<Any>? {
        var array = ArrayList<Any>()
        var childLinks = doc?.select("div.lb_box")
        if (childLinks == null || childLinks?.size == 0) {
            childLinks = doc?.select("div.mode_box")
        }
        if (childLinks == null || childLinks?.size == 0) {
            childLinks = doc?.select("div.star_box")
        }
        if (childLinks != null && childLinks.size > 0) {
            var items = childLinks?.get(0)?.getElementsByTag("dl")
            items?.forEach { i ->
                var dt = i.getElementsByTag("dt")?.get(0)
                var url = dt?.getElementsByTag("a")?.attr("href")
                var title = dt?.getElementsByTag("img")?.attr("alt")
                var image = dt?.getElementsByTag("img")?.attr("src")
                var extra = i.getElementsByTag("dd")?.get(0)?.getElementsByTag("span")?.text()
                array.add(CatalogInfo(title, url, image, extra, null))
            }
        }
        return array
    }

}