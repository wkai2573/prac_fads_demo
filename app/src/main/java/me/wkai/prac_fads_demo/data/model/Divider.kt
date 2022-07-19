package me.wkai.prac_fads_demo.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Divider(
	@Json(name = "_meta")
	var meta:Meta? = null,
	@Json(name = "title")
	var title:String? = null,
	@Json(name = "type")
	override var type:String? = null
) : Vector()