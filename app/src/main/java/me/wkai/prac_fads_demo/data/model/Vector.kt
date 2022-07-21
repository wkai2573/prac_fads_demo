package me.wkai.prac_fads_demo.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VectorJson(
	@Json(name = "type")
	var type:String? = null,
	@Json(name = "appearance")
	var appearance:Appearance? = null,
	@Json(name = "extra")
	var extra:Extra? = null,
	@Json(name = "_meta")
	var meta:Meta? = null,
	@Json(name = "ref")
	var ref:String? = null,
	@Json(name = "source")
	var source:String? = null,
	@Json(name = "title")
	var title:String? = null,
	@Json(name = "items")
	var items:List<News>? = null,
	@Json(name = "style")
	var style:String? = null,
)

abstract class Vector {
	open val type:String? = null
}
