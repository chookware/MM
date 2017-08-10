package com.viva.photo.control

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import java.lang.Exception

open abstract class BaseParser {

    protected var doc: Document? = null

    constructor(url: String) {
        try {
            doc = Jsoup.connect(url).timeout(3000).get()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun isReadied(): Boolean {
        return doc != null
    }

    abstract fun stringOfHtml(): String?

}
