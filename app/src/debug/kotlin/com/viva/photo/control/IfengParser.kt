package com.viva.photo.control

class IfengParser : BaseParser {

    constructor():super("http://www.ifeng.com")

    fun getTitle(): String? {
        return doc?.title()
    }

    fun getTopNews(): String? {
        var element = doc?.getElementById("headLineDefault")?.select("ul.FNewMTopLis")
        var links = element?.select("li")
        return links?.toString()
    }

}
