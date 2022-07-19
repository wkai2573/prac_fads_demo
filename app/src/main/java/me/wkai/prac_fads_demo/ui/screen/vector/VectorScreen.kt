package me.wkai.prac_fads_demo.ui.screen.vector

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import me.wkai.prac_fads_demo.data.model.Vector

@Composable
fun VectorScreen(
	viewModel:VectorViewModel = hiltViewModel()
) {

	val vectorList by viewModel.state.collectAsState()

	LazyColumn {
		items(vectorList) {
			VectorCard(it)
		}
	}
}

@Composable
fun VectorCard(vector:Vector?) {
	vector?.let {
		Column {
			Text(it.toString(), maxLines = 3)
			Divider(Modifier.padding(vertical = 4.dp))
		}
	}
}
