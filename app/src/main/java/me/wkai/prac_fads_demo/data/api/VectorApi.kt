package me.wkai.prac_fads_demo.data.api

import me.wkai.prac_fads_demo.data.model.News
import me.wkai.prac_fads_demo.data.model.Vector
import me.wkai.prac_fads_demo.data.model.VectorResponse
import retrofit2.http.*


interface VectorApi {

	@GET(ApiConst.GET_VECTOR)
	suspend fun getVector():String

}