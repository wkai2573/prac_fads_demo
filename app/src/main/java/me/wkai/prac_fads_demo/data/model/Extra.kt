package me.wkai.prac_fads_demo.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Extra(
	@Json(name = "created")
	val created:Int? = null,
	@Json(name = "description")
	val description:String? = null
)