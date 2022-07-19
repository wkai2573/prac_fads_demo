package me.wkai.prac_fads_demo.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VectorResponse(
  @Json(name = "getVector")
  val getVector: GetVector
)