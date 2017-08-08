package com.viva.photo.control

import org.jsoup.Jsoup

class Paraser {

    fun test() : String {
        var doc = Jsoup.connect("http://www.ifeng.com/").get()
        return doc.toString()

    }

}
