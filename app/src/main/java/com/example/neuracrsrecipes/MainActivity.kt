package com.example.neuracrsrecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.design_system.theming.NeuracrTheme
import com.example.neuracrsrecipes.navigation.NavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			NeuracrTheme {
				NavHost(this)
			}
		}
	}
}
