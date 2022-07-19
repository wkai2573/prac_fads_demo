package me.wkai.prac_fads_demo.data.model


import android.content.ClipData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetVector(
  @Json(name = "items")
  val items: List<Vector>
)