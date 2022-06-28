package me.wkai.prac_fads_demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import me.wkai.prac_fads_demo.type.News
import java.text.SimpleDateFormat
import java.util.*

//新聞單卡高度
const val newsItemHeight = 120

//新聞列表
@Composable
fun MyApp(appViewModel:AppViewModel) {

	val newsList = appViewModel.newsList.observeAsState(listOf())
	val dividerList = appViewModel.dividerList.observeAsState(listOf())

	Column(Modifier.fillMaxWidth()) {
		LazyColumn() {
			itemsIndexed(items = dividerList.value) { index, it ->
				NewsGroup(index, it.title, newsList.value)
			}
		}
	}

}

//章節區塊
@Composable
fun NewsGroup(index:Int, title:String, newsList:List<News>) {

	val matchNewsList = newsList.filter { it.section == title }
	if (matchNewsList.isEmpty()) return

	Column() {
		if (index != 0) {
			Divider(Modifier.fillMaxWidth().padding(vertical = 4.dp))
		}
		Text(text = title, modifier = Modifier.padding(8.dp), fontSize = 20.sp, fontWeight = FontWeight.Bold)
	}
	LazyColumn(Modifier.height((matchNewsList.size * newsItemHeight).dp)) {
		items(matchNewsList) {
			NewsCard(imgUrl = it.thumbnail, time = it.created, title = it.title, from = it.subTitle, subscript = it.subscript)
		}
	}
}

//新聞卡
@Composable
fun NewsCard(imgUrl:String, time:Long, title:String, from:String, subscript:String) {
	Row (Modifier.height(newsItemHeight.dp).padding(vertical = 8.dp)) {
		Card(modifier = Modifier.width(150.dp).padding(horizontal = 4.dp)) {
			Box {
				AsyncImage(
					modifier = Modifier.fillMaxSize(),
					model = imgUrl,
					contentDescription = null,
					contentScale = ContentScale.Crop,
				)
				Text(
					text = subscript,
					fontSize = 10.sp,
					color = Color.White,
					modifier = Modifier.padding(4.dp).background(Color(0x88000000)).align(Alignment.BottomEnd).padding(2.dp)
				)
			}
		}
		Column(Modifier.padding(horizontal = 8.dp).align(Alignment.CenterVertically)) {
			Text(text = getDateTime(time), fontSize = 12.sp, modifier = Modifier.padding(bottom = 2.dp))
			Text(text = title, fontSize = 14.sp, modifier = Modifier.padding(bottom = 2.dp))
			Text(text = from, fontSize = 12.sp, color = Color.Gray)
		}
	}
}

//時間:Long -> String
private fun getDateTime(l:Long):String {
	try {
		val sdf = SimpleDateFormat("MMM dd, yyyy 'at' h:mm aaa", Locale.US)
		val netDate = Date(l * 1000)
		return sdf.format(netDate)
	} catch (e:Exception) {
		return ""
	}
}