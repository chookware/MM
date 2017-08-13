package com.viva.photo.control

import com.viva.photo.utils.LogUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import java.lang.Exception

open class BaseParser {

    var doc: Document? = null

    constructor(url: String?) {
        try {
            doc = Jsoup.connect(url).timeout(3000).get()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun isReadied(): Boolean {
        return doc != null
    }

    open fun stringOfHtml(): String? {
        return null
    }

    fun clear() {
        doc?.empty()
    }

}
