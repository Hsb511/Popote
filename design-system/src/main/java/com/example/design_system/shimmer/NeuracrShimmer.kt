package com.example.design_system.shimmer

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.example.design_system.theming.NeuracrTheme
import com.example.design_system.theming.black90
import com.example.design_system.theming.black95

@Composable
fun NeuracrShimmer(modifier: Modifier = Modifier) {
	Box(modifier = modifier
		.clip(shape = MaterialTheme.shapes.medium)
		.background(shimmerBrush())) {}
}

@Composable
@Preview(showSystemUi = true)
private fun NeuracrShimmerPreview() {
	NeuracrTheme {
		NeuracrShimmer()
	}
}

@Composable
private fun shimmerBrush(): Brush {
	// These colors will be used on the brush. The lightest color should be in the middle
	val gradient = listOf(
		black90(),
		black95(),
		black90(),
	)
	val transition = rememberInfiniteTransition() // animate infinite times
	val translateAnimation = transition.animateFloat( // animate the transition
		initialValue = -TRANSITION_VALUE,
		targetValue = TRANSITION_VALUE,
		animationSpec = infiniteRepeatable(
			animation = tween(
				durationMillis = TRANSITION_DURATION_IN_MILLIS, // duration for the animation
				easing = FastOutLinearInEasing
			)
		)
	)
	return Brush.linearGradient(
		colors = gradient,
		start = Offset(x = TRANSITION_GRADIENT, y = TRANSITION_GRADIENT),
		end = Offset(
			x = translateAnimation.value,
			y = translateAnimation.value
		)
	)
}

private const val TRANSITION_VALUE = 1000f
private const val TRANSITION_DURATION_IN_MILLIS = 1000
private const val TRANSITION_GRADIENT = 200f
