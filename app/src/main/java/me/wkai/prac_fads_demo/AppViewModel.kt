package me.wkai.prac_fads_demo

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.wkai.prac_fads_demo.type.Divider
import me.wkai.prac_fads_demo.type.News
import org.json.JSONObject

class AppViewModel(application:Application) : AndroidViewModel(application) {

	var isLoading by mutableStateOf(false)

	val dividerList = MutableLiveData<List<Divider>>(listOf())
	val newsList = MutableLiveData<List<News>>(listOf())

	fun getNews() {
		isLoading = true
		viewModelScope.launch(Dispatchers.IO) {
			request_getNews {}
		}.invokeOnCompletion {
			isLoading = false
		}
	}

	//請求
	private fun request_getNews(onFailure:(String) -> Unit):Boolean {

		val _dividerList:MutableList<Divider> = mutableListOf()
		val _newsList:MutableList<News> = mutableListOf()

		var responseText:String
		val url = Urls.getNews
		runCatching {
			responseText = request(url)
			Log.i("@@@", responseText)
			//解析json 轉成newsList & dividerList
			val jo = JSONObject(responseText)
			val jo_v = jo.getJSONObject("getVector")
			val ja_items = jo_v.getJSONArray("items")
			for (i in 0 until ja_items.length()) {
				val itemJo = ja_items.getJSONObject(i)
				val type = if (itemJo.isNull("type")) "" else itemJo.getString("type")
				when (type) {
					"divider" -> {
						val title = if (itemJo.isNull("title")) null else itemJo.getString("title")
						title?.let { _dividerList.add(Divider(it)) }
					}
					"news" -> {
						var mainTitle:String? = null
						var subTitle:String? = null
						var thumbnail:String? = null
						var subscript:String? = null
						var created:Long? = null
						var section:String? = null

						val appearance = if (itemJo.isNull("appearance")) null else itemJo.getJSONObject("appearance")
						if (appearance != null) {
							mainTitle = if (appearance.isNull("mainTitle")) null else appearance.getString("mainTitle")
							subTitle = if (appearance.isNull("subTitle")) null else appearance.getString("subTitle")
							thumbnail = if (appearance.isNull("thumbnail")) null else appearance.getString("thumbnail")
							subscript = if (appearance.isNull("subscript")) null else appearance.getString("subscript")
						}
						val extra = if (itemJo.isNull("extra")) null else itemJo.getJSONObject("extra")
						if (extra != null) {
							created = if (extra.isNull("created")) null else extra.getLong("created")
						}
						val _meta = if (itemJo.isNull("_meta")) null else itemJo.getJSONObject("_meta")
						if (_meta != null) {
							section = if (_meta.isNull("section")) null else _meta.getString("section")
						}
						//屬性皆存在, 加入news
						if (thumbnail != null && created != null && section != null) {
							_newsList.add(News(mainTitle?:"", subTitle?:"", subscript?:"", thumbnail, created, section))
						}
					}
				}
			}
			newsList.postValue(_newsList)
			dividerList.postValue(_dividerList)

		}.onFailure {
			return false
		}
		return true
	}

}