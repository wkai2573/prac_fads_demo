package me.wkai.prac_fads_demo.ui.screen.vector

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
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
		getVector()
	}

	//==方法==
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

	//==事件==
}