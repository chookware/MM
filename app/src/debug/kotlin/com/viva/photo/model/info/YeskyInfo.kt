package com.viva.photo.control.info

data class MenuInfo(var title: String?, var url: String?, var image: String?, var item: MutableList<MenuInfo>?)
data class CatalogInfo(var title: String?, var url: String?, var image: String?, var extra: String?, var item: MutableList<MenuInfo>?)