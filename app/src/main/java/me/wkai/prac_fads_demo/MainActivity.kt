package me.wkai.prac_fads_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import me.wkai.prac_fads_demo.ui.theme.prac_fads_demoTheme

class MainActivity : ComponentActivity() {

	private val appViewModel:AppViewModel by viewModels()

	override fun onCreate(savedInstanceState:Bundle?) {
		super.onCreate(savedInstanceState)

		appViewModel.getNews()

		setContent {
			prac_fads_demoTheme {
				// A surface container using the 'background' color from the theme
				Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
					MyApp(appViewModel)
				}
			}
		}
	}
}
