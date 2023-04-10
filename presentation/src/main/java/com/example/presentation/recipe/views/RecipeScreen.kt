package com.example.presentation.recipe.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.presentation.recipe.extensions.toUrlRecipeId

@Composable
fun RecipeScreen(cleanRecipeId: String?, modifier: Modifier) {
	RecipeScreen(cleanRecipeId?.toUrlRecipeId() ?: "", "", modifier)
}

@Composable
fun RecipeScreen(cleanRecipeId: String, t: String,  modifier: Modifier) {
	Column(modifier = modifier) {
		Text(text = cleanRecipeId)
	}
}
