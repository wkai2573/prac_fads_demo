package me.wkai.prac_fads_demo

import android.app.Application
import co.infinum.retromock.Retromock
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VectorApplication : Application() {
	companion object{
		//使用假資料
		const val useMockData = false
	}
}