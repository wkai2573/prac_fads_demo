package me.wkai.prac_fads_demo.ui.screen.vector

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import me.wkai.prac_fads_demo.data.model.Vector
import me.wkai.prac_fads_demo.data.repository.VectorRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class VectorViewModel @Inject constructor(
	private val repository:VectorRepository
) : ViewModel() {


	//變數宣告 vm_val:state
	private val _isLoading = MutableStateFlow(false)
	val isLoading:StateFlow<Boolean> = _isLoading

	private val _state = MutableStateFlow(emptyList<Vector>())
	val state:StateFlow<List<Vector>> = _state

	private var job:Job? = null

	//初始化 (當進入此screen時,要立即取資料的話,才需要)
	init {
		getVector()
	}

	private fun getVector() {
		job?.cancel()
		job = repository.getVector()
			.onStart {
				//請求前處理
				_isLoading.value = true
			}
			.onEach {
				//成功處理
				_state.value = it
			}
			.onCompletion {
				//結束處理
				_isLoading.value = false
			}
			.catch {
				//異常處理
				Log.i("@@@", it.message ?: "unknown error")
			}
			.launchIn(viewModelScope)
	}

	//事件 vm:event
}