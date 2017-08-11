package com.viva.photo.control

import com.viva.photo.control.info.MenuInfo


class YeskyParser: BaseParser {

    constructor():super("http://pic.yesky.com")

    override fun stringOfHtml(): String? {
        return doc?.toString()
    }

    fun getMenu(): ArrayList<MenuInfo>? {
        var links = doc?.select("ul.nav_left")?.get(0)?.getElementsByTag("a")
        var array = arrayListOf<MenuInfo>()
        if (links != null) {
            for (element in links) {
                var menuInfo = MenuInfo(element?.text(), element?.attr("href"), null)
                var childParser = BaseParser(menuInfo.url)
                var childLinks = childParser.doc?.select("div.lb_box")
                if (childLinks == null || childLinks?.size == 0) {
                    childLinks = childParser.doc?.select("div.mode_box")
                }
                if (childLinks == null || childLinks?.size == 0) {
                    childLinks = childParser.doc?.select("div.star_box")
                }
                if (childLinks != null && childLinks.size > 0) {
                    menuInfo.image = childLinks?.get(0)?.getElementsByTag("img")?.get(0)?.attr("src")
                }
                array.add(menuInfo)
            }
        }
        return array
    }

}