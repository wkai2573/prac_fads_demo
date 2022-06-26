package me.wkai.prac_fads_demo

import com.github.kittinunf.fuel.httpGet
import kotlin.jvm.Throws

object Urls {

	val getNews
		get() = "https://static.mixerbox.com/interview/interview_get_vector.json"

}

//請求
@Throws
fun request(url:String):String {
	val (request, response, result) = url.httpGet().responseString()
	return result.fold(
		failure = { throw it }, //連線失敗
		success = { responseText -> responseText },
	)
}