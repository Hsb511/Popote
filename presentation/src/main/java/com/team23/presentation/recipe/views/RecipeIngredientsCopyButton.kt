package com.team23.presentation.recipe.views

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.presentation.R

@Composable
fun RecipeIngredientsCopyButton(
	ingredientsList: List<String>,
	modifier: Modifier = Modifier,
) {
	val clipboardManager = LocalClipboardManager.current

	IconButton(
		onClick = {
			clipboardManager.setText(
				AnnotatedString(
					ingredientsList.joinToString(
						prefix = " ${Typography.bullet} ",
						separator = "\n ${Typography.bullet} "
					)
				)
			)
		},
		modifier = modifier.offset(x = 8.dp, y = 8.dp)
	) {
		Icon(
			painter = painterResource(id = R.drawable.ic_content_copy),
			contentDescription = stringResource(id = R.string.recipe_copy_to_clipboard_a11y),
			tint = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.69f),
		)
	}
}

@Composable
@Preview(showBackground = true)
fun RecipeIngredientsCopyButtonPreview() {
	MaterialTheme {
		RecipeIngredientsCopyButton(
			ingredientsList = listOf(
				"0.5 - lime",
				"15 mL - sugar syrup",
				"12 - raspberry (frozen)",
				"12 - mint leaf",
			),
		)
	}
}
