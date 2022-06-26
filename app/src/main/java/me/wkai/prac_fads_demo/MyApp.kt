package me.wkai.prac_fads_demo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import me.wkai.prac_fads_demo.type.News
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun MyApp(appViewModel:AppViewModel) {

	val newsList = appViewModel.newsList.observeAsState(listOf())
	val dividerList = appViewModel.dividerList.observeAsState(listOf())

	Column(Modifier.fillMaxWidth()) {
		LazyColumn() {
//			item {
//				NewsCard(imgUrl = "https://tedblob.com/wp-content/uploads/2021/09/android.png", time = 0L, title = "2", from = "3")
//			}
			items(items = dividerList.value) {
				NewsGroup(it.title, newsList.value)
			}
		}
	}

}

@Composable
fun NewsGroup(title:String, newsList:List<News>) {

	val matchNewsList = newsList.filter { it.section == title }
	if (matchNewsList.isEmpty()) return;

	Column() {
		Divider(Modifier.fillMaxWidth().padding(vertical = 4.dp))
		Text(text = title, modifier = Modifier.padding(8.dp), fontSize = 20.sp)
	}
	LazyColumn(Modifier.height(300.dp)) {
		items(matchNewsList) {
			NewsCard(imgUrl = it.thumbnail, time = it.created ?: 0L, title = it.title, from = it.subTitle)
		}
	}
}

@Composable
fun NewsCard(imgUrl:String, time:Long, title:String, from:String) {
	Row(
		Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
	) {
		AsyncImage(
			modifier = Modifier.size(100.dp, 100.dp).padding(4.dp),
			model = imgUrl,
			contentDescription = null
		)
		Column(Modifier.padding(4.dp)) {
			Text(text = getDateTime(time))
			Text(text = title)
			Text(text = from)
		}
	}
}

private fun getDateTime(l:Long):String {
	try {
		val sdf = SimpleDateFormat("MM/dd/yyyy")
		val netDate = Date(l * 1000)
		return sdf.format(netDate)
	} catch (e:Exception) {
		return ""
	}
}