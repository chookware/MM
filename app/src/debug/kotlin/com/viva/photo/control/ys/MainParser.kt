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
        var card1 = arrayListOf<Any>()
        var card2 = arrayListOf<Any>()
        var card3 = arrayListOf<Any>()

        var links = doc?.select("div.lqtp_con")
        if (links != null && links!!.size > 0) {
            var childs = links?.get(0)?.select("dt>a")
            if (childs != null && childs!!.size > 0) {
                card1.add(MenuInfo(null, childs?.get(0)?.attr("href"), childs?.get(0)?.getElementsByTag("img")?.attr("src"), null))
                card1.add(MenuInfo(null, childs?.get(1)?.attr("href"), childs?.get(1)?.getElementsByTag("img")?.attr("src"), null))
                card1.add(MenuInfo(null, childs?.get(2)?.attr("href"), childs?.get(2)?.getElementsByTag("img")?.attr("src"), null))
            }
            childs = links?.get(1)?.select("dt>a")
            if (childs != null && childs!!.size > 0) {
                card2.add(MenuInfo(null, childs?.get(0)?.attr("href"), childs?.get(0)?.getElementsByTag("img")?.attr("src"), null))
                card2.add(MenuInfo(null, childs?.get(1)?.attr("href"), childs?.get(1)?.getElementsByTag("img")?.attr("src"), null))
            }
        }

        links = doc?.select("ul.lqxw")
        if (links != null && links!!.size > 0) {
            var childs = links?.get(0)?.getElementsByTag("a")
            childs?.forEach {
                i ->
                card1.add(MenuInfo(i.attr("title"), i.attr("href"), null, null))
            }
            childs = links?.get(1)?.getElementsByTag("a")
            childs?.forEach {
                i ->
                card2.add(MenuInfo(i.attr("title"), i.attr("href"), null, null))
            }
        }

        links = doc?.select("div.ylzt_con")
        if (links != null && links!!.size > 0) {
            var childs = links?.get(0)?.select("dt>a")
            if (childs != null && childs!!.size > 0) {
                card3.add(MenuInfo(null, childs?.get(0)?.attr("href"), childs?.get(0)?.getElementsByTag("img")?.attr("src"), null))
                card3.add(MenuInfo(null, childs?.get(1)?.attr("href"), childs?.get(1)?.getElementsByTag("img")?.attr("src"), null))
            }
        }

        links = doc?.select("div.ylztxw")
        if (links != null && links!!.size > 0) {
            var childs = links?.get(0)?.getElementsByTag("a")
            childs?.forEach {
                i ->
                card3.add(MenuInfo(i.attr("title"), i.attr("href"), null, null))
            }
            LogUtils.v("ccc card3: " + card3)
        }

        /**
         * catalog
         */
        links = doc?.select("ul.nav_left")?.get(0)?.getElementsByTag("a")
        var menu = arrayListOf<Any>()
        if (links != null) {
            for (element in links) {
                var menuInfo = MenuInfo(element?.text(), element?.attr("href"), null, null)
                menu.add(menuInfo)
            }
        }

        var array = arrayListOf<Any>()
        array.add(card1)
        array.add(card2)
        array.add(card3)
        array.add(menu)

        return array
    }

}