package me.wkai.prac_fads_demo.ui.screen.vector

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import me.wkai.prac_fads_demo.data.model.Vector
import me.wkai.prac_fads_demo.data.repository.VectorRepository
import javax.inject.Inject

@HiltViewModel
class VectorViewModel @Inject constructor(
	private val repository:VectorRepository
) : ViewModel() {

	//==變數==
	private val _isLoading = MutableStateFlow(false)
	val isLoading:StateFlow<Boolean> = _isLoading

	private val _state = MutableStateFlow(emptyList<Vector>())
	val state:StateFlow<List<Vector>> = _state

	private var job:Job? = null

	//==初始化==
	init {
		getVector2()
	}

	//==方法==
	//取得新聞清單
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

	//取得新聞清單 (Flow分割)
	private fun getVector2() {
		job?.cancel()
		job = repository.getVector().commonHandle {
			_state.value = it
		}
	}

	//通用處理
	private fun <T> Flow<T>.commonHandle(onEach:suspend (T) -> Unit):Job {
		return this
			.onStart { _isLoading.value = true }
			.onCompletion { _isLoading.value = false }
			.onEach { onEach(it) }
			.catch { errorHandle(it) }
			.launchIn(viewModelScope)
	}

	private fun errorHandle(it:Throwable) {
		Log.i("@@@", it.message ?: "unknown error")
	}

	//==事件==
}