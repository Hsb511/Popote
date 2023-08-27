package com.team23.presentation.recipe.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.R

@Composable
fun RecipeModifyButton(
	onUpdateRecipe: () -> Unit,
	onDeleteRecipe: () -> Unit,
	modifier: Modifier = Modifier,
) {
	var isExpanded by remember { mutableStateOf(false) }
	var openDialog by remember { mutableStateOf(false) }
	val rotation by animateFloatAsState(
		targetValue = if (isExpanded) -225f else 0f,
		animationSpec = getTween(),
	)

	Column(horizontalAlignment = Alignment.End) {
		AnimatedVisibility(
			visible = isExpanded,
			enter = fadeIn(getTween(delayMillis = 2 * ANIMATION_DELAY)),
			exit = fadeOut(getTween()) + shrinkVertically(getTween()),
		) {
			RecipeFabRowButton(
				text = stringResource(id = R.string.recipe_update_label),
				icon = Icons.Outlined.Refresh,
				onIconClick = onUpdateRecipe,
				modifier = Modifier.padding(bottom = 8.dp),
			)
		}
		AnimatedVisibility(
			visible = isExpanded,
			enter = fadeIn(getTween(delayMillis = ANIMATION_DELAY)),
			exit = fadeOut(getTween()) + shrinkVertically(getTween()),
		) {
			RecipeFabRowButton(
				text = stringResource(id = R.string.recipe_delete_label),
				icon = Icons.Outlined.Delete,
				onIconClick = { openDialog = true },
				modifier = Modifier.padding(bottom = 8.dp),
			)
		}
		FloatingActionButton(
			containerColor = MaterialTheme.colorScheme.secondary,
			contentColor = MaterialTheme.colorScheme.onSurface,
			onClick = { isExpanded = !isExpanded },
			modifier = modifier.size(56.dp),
		) {
			Icon(
				imageVector = Icons.Outlined.Edit,
				contentDescription = null,
				modifier = Modifier
					.size(24.dp)
					.rotate(rotation),
			)
		}
	}

	if (openDialog) {
		AlertDialog(
			onDismissRequest = { openDialog = false },
			title = { Text(stringResource(id = R.string.recipe_delete_dialog_title)) },
			text = { Text(stringResource(id = R.string.recipe_delete_dialog_text)) },
			confirmButton = {
				TextButton(
					onClick = {
						openDialog = false
						onDeleteRecipe()
					}
				) { Text(stringResource(id = R.string.dialog_confirm)) }
			},
			dismissButton = {
				TextButton(
					onClick = { openDialog = false }
				) { Text(stringResource(id = R.string.dialog_dismiss)) }
			}
		)
	}
}

@Composable
private fun RecipeFabRowButton(
	text: String,
	icon: ImageVector,
	onIconClick: () -> Unit,
	modifier: Modifier = Modifier,
) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = modifier,
	) {
		Text(text = text)
		FloatingActionButton(
			containerColor = MaterialTheme.colorScheme.secondary,
			contentColor = MaterialTheme.colorScheme.onSurface,
			onClick = onIconClick,
			modifier = Modifier
				.padding(horizontal = 12.dp)
				.size(32.dp),
		) {
			Icon(
				imageVector = icon,
				contentDescription = null,
			)
		}
	}
}

@Composable
@Preview(showBackground = true)
fun RecipeModifyButtonPreview() {
	NeuracrTheme {
		RecipeModifyButton({}, {})
	}
}

private const val ANIMATION_DURATION_MILLIS = 500
private const val ANIMATION_DELAY = 300

private fun <T> getTween(delayMillis: Int = 0) = tween<T>(
	durationMillis = ANIMATION_DURATION_MILLIS,
	delayMillis = delayMillis,
)
