package me.wkai.prac_fads_demo.data.model.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import me.wkai.prac_fads_demo.data.model.*

class VectorAdapter {
	@FromJson
	fun fromJson(vectorJson:VectorJson):Vector? {
		return when (vectorJson.type) {
			"news" -> {
				val news = News()
				news.type = vectorJson.type
				news.appearance = vectorJson.appearance
				news.extra = vectorJson.extra
				news.meta = vectorJson.meta
				news.ref = vectorJson.ref
				news.source = vectorJson.source
				news
			}
			"divider" -> {
				val divider = Divider()
				divider.type = vectorJson.type
				divider.meta = vectorJson.meta
				divider.title = vectorJson.title
				divider
			}
			"carousel" -> {
				val carousel = Carousel()
				carousel.type = vectorJson.type
				carousel.items = vectorJson.items
				carousel.meta = vectorJson.meta
				carousel.style = vectorJson.style
				carousel
			}
			else -> null
		}
	}

	@ToJson
	fun toJson(vector:Vector):VectorJson {
		val json = VectorJson()
		when (vector) {
			is News -> {
				json.type = "news"
				json.appearance = vector.appearance
				json.extra = vector.extra
				json.meta = vector.meta
				json.ref = vector.ref
				json.source = vector.source
			}
			is Divider -> {
				json.type = "divider"
				json.meta = vector.meta
				json.title = vector.title
			}
			is Carousel -> {
				json.type = "carousel"
				json.items = vector.items
				json.meta = vector.meta
				json.style = vector.style
			}
		}
		return json
	}
}