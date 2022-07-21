package me.wkai.prac_fads_demo.ui.screen.vector


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import me.wkai.prac_fads_demo.data.model.Carousel
import me.wkai.prac_fads_demo.data.model.Divider
import me.wkai.prac_fads_demo.data.model.News

@Composable
fun VectorScreen(
	viewModel:VectorViewModel = hiltViewModel()
) {

	val vectorList by viewModel.state.collectAsState()

	val newsList = mutableListOf<News>()
	val dividerList = mutableListOf<Divider>()

	vectorList.forEach { vector ->
		when (vector) {
			is News -> newsList.add(vector)
			is Divider -> dividerList.add(vector)
			is Carousel -> {
				vector.items?.let { newsList.addAll(it) }
			}
		}
	}

	LazyColumn(
		modifier = Modifier.fillMaxSize()
	) {
		items(dividerList) {
			DividerCard(it, newsList.filter { news -> news.meta?.section == it.title })
		}
	}
}

@Composable
fun DividerCard(divider:Divider, newsList:List<News>) {
	if (newsList.isEmpty()) return
	divider.title?.let {
		Text(
			modifier = Modifier.padding(8.dp),
			text = divider.title!!,
			style = MaterialTheme.typography.h4,
		)
		Column() {
			newsList.forEachIndexed { index, news ->
				NewsCard(news = news)
				Divider(Modifier.padding(vertical = 8.dp))
			}
		}
	}
}

@Composable
fun NewsCard(news:News) {
	Surface {
		Row(
			modifier = Modifier.height(120.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			Box(
				modifier = Modifier.weight(2f).padding(4.dp),
			) {
				AsyncImage(
					model = news.appearance?.thumbnail ?: "",
					contentDescription = null,
					contentScale = ContentScale.Crop,
				)
				news.appearance?.subscript?.let {
					Text(
						text = it,
						color = MaterialTheme.colors.surface,
						style = MaterialTheme.typography.body2,
						modifier = Modifier
							.align(Alignment.BottomEnd)
							.padding(4.dp)
							.background(Color.Black.copy(alpha = 0.4f))
							.padding(horizontal = 4.dp)
					)
				}
			}
			Column(
				modifier = Modifier.weight(3f).padding(horizontal = 4.dp)
			) {
				news.appearance?.mainTitle?.let { Text(text = it, style = MaterialTheme.typography.body2) }
				news.appearance?.subTitle?.let { Text(text = it, style = MaterialTheme.typography.body2) }
				news.appearance?.subscript?.let { Text(text = it, style = MaterialTheme.typography.body2) }
			}
		}
	}
}

//@Composable
//fun VectorCard(vector:Vector?) {
//	vector?.let {
//		Column {
//			Text(it.toString(), maxLines = 3)
//			Divider(Modifier.padding(vertical = 4.dp))
//		}
//	}
//}
