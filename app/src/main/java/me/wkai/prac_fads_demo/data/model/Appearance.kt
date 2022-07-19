package me.wkai.prac_fads_demo.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Appearance(
	@Json(name = "mainTitle")
	val mainTitle:String? = null,
	@Json(name = "subTitle")
	val subTitle:String? = null,
	@Json(name = "subscript")
	val subscript:String? = null,
	@Json(name = "thumbnail")
	val thumbnail:String? = null
)