package me.wkai.prac_fads_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import me.wkai.prac_fads_demo.ui.screen.vector.VectorScreen
import me.wkai.prac_fads_demo.ui.theme.prac_fads_demoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState:Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			prac_fads_demoTheme {
				Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
					VectorScreen()
				}
			}
		}
	}
}