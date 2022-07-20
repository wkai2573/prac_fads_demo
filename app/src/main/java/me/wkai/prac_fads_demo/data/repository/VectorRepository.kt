package me.wkai.prac_fads_demo.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.wkai.VectorApplication.Companion.useMockData
import me.wkai.prac_fads_demo.data.api.VectorApi
import me.wkai.prac_fads_demo.data.model.Vector
import javax.inject.Inject


class VectorRepository @Inject constructor(
	private val api:VectorApi,
) {

	//取得Vector (包flow)
	fun getVector():Flow<List<Vector>> = flow {
		val vectorResponse = if (!useMockData) api.getVector() else api.getVector_mock()
		val vg = vectorResponse.getVector
		val vectorList = vg.items
		emit(vectorList)
	}

}