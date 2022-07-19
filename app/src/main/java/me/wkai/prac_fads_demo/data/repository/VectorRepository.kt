package me.wkai.prac_fads_demo.data.repository

import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.wkai.prac_fads_demo.data.api.VectorApi
import me.wkai.prac_fads_demo.data.model.Vector
import me.wkai.prac_fads_demo.data.model.VectorResponse
import javax.inject.Inject


class VectorRepository @Inject constructor(
	private val api:VectorApi,
	private val vectorResponseAdapter:JsonAdapter<VectorResponse>,
	private val vectorAdapter:JsonAdapter<Vector>,
) {

	//轉flow (建議)
	fun getVector():Flow<List<Vector>> = flow {
//		val vectorResponse = api.getVector()
//		val vg = vectorResponse.getVector
//		val vectorList = vg.items
//		emit(vectorList)

		val json = api.getVector()
		val vectorResponse:VectorResponse = vectorResponseAdapter.fromJson(json)!!
		val vectorList = vectorResponse.getVector.items

		emit(vectorList)
	}

}