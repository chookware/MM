package com.viva.photo.control.ys

import com.viva.photo.control.BaseParser

class ViewListParser(url: String): BaseParser(url) {

    private var hasNext: Boolean = false

    override fun parser(): ArrayList<Any>? {
        return super.parser()
    }

    override fun hasNext(): Boolean {
        return hasNext
    }

}
