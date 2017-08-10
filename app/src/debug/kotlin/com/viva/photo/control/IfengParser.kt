package com.viva.photo.control

class IfengParser : BaseParser {

    constructor():super("http://www.ifeng.com")

    fun getTitle(): String? {
        return doc?.title()
    }

    override fun stringOfHtml(): String? {
        return doc?.toString()
    }

    fun getTopNews(): String? {
        var element = doc?.getElementById("headLineDefault")?.select("ul.FNewMTopLis")
        var links = element?.select("li")
        return links?.toString()
    }

}
