package com.viva.photo.control.ys

import com.viva.photo.control.BaseParser
import com.viva.photo.control.info.MenuInfo
import com.viva.photo.utils.LogUtils


class MainParser : BaseParser {

    constructor():super("http://pic.yesky.com")

    /*override fun parser(): ArrayList<Any>? {
        var links = doc?.select("ul.nav_left")?.get(0)?.getElementsByTag("a")
        var array = arrayListOf<Any>()
        if (links != null) {
            for (element in links) {
                var menuInfo = MenuInfo(element?.text(), element?.attr("href"), null, mutableListOf())
                var childParser = BaseParser(menuInfo.url)
                childParser.connect()
                var childLinks = childParser.doc?.select("div.lb_box")
                if (childLinks == null || childLinks?.size == 0) {
                    childLinks = childParser.doc?.select("div.mode_box")
                }
                if (childLinks == null || childLinks?.size == 0) {
                    childLinks = childParser.doc?.select("div.star_box")
                }
                if (childLinks != null && childLinks.size > 0) {
                    menuInfo.image = childLinks?.get(0)?.getElementsByTag("img")?.get(0)?.attr("src")
                    var items = childLinks?.get(0)?.getElementsByTag("dt")
                    items?.forEach { i ->
                        if (menuInfo.item!!.size >= 10) {
                        } else {
                            var url = i.getElementsByTag("a")?.attr("href")
                            var title = i.getElementsByTag("img")?.attr("alt")
                            menuInfo.item?.add(MenuInfo(title, url, null, null))
                        }
                    }
                    try {
                        childParser.clear()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                array.add(menuInfo)
            }
        }
        return array
    }*/

    override fun parser(): ArrayList<Any>? {

        /**
         * card
         */


        /**
         * catalog
         */
        var links = doc?.select("ul.nav_left")?.get(0)?.getElementsByTag("a")
        var array = arrayListOf<Any>()
        if (links != null) {
            for (element in links) {
                var menuInfo = MenuInfo(element?.text(), element?.attr("href"), null, null)
                array.add(menuInfo)
            }
        }
        return array
    }

}