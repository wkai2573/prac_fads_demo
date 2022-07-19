package me.wkai.prac_fads_demo.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Carousel(
	@Json(name = "items")
	var items:List<News>? = null,
	@Json(name = "_meta")
	var meta:Meta? = null,
	@Json(name = "style")
	var style:String? = null,
	@Json(name = "type")
	override var type:String? = null
) : Vector()