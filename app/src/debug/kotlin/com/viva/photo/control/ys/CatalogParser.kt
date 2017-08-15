package com.viva.photo.control.ys

import com.viva.photo.control.BaseParser
import com.viva.photo.control.info.CatalogInfo

class CatalogParser(url: String?): BaseParser(url) {

    private var hasNext: Boolean = false

    override fun parser(): ArrayList<Any>? {

        hasNext = false

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

        childLinks = doc?.select("div.flym")
        if (childLinks != null && childLinks.size > 0) {
            var items = childLinks?.get(0)?.getElementsByTag("a")
            items?.forEach {
                i ->
                if (i.text().contentEquals("下一页")) {
                    hasNext = true
                    url = "http://www.yesky.com/pic" + i.attr("href")
                }
            }
        }
        return array
    }

    override fun hasNext(): Boolean {
        return hasNext
    }

}