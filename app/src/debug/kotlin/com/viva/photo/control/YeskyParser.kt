package com.viva.photo.control

class YeskyParser: BaseParser {

    constructor():super("http://pic.yesky.com")

    override fun stringOfHtml(): String? {
        return doc?.toString()
    }

}