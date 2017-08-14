package com.viva.photo.control

import com.viva.photo.utils.LogUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import java.lang.Exception

open class BaseParser {

    var doc: Document? = null
    private var url: String? = null

    constructor(url: String?) {
        this.url = url
    }

    fun connect() {
        try {
            doc = Jsoup.connect(url).timeout(3000).get()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun isReadied(): Boolean {
        return doc != null
    }

    fun stringOfHtml(): String? {
        return doc?.toString()
    }

    open fun parser(): ArrayList<Any>? {
        return null
    }

    fun clear() {
        doc?.empty()
    }

}
