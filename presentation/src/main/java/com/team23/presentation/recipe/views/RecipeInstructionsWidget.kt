package com.team23.presentation.recipe.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.recipe.models.InstructionUiModel

@Composable
fun RecipeInstructionsWidget(
	instructions: List<InstructionUiModel>,
	modifier: Modifier = Modifier,
) {
	OutlinedCard(
		colors = CardDefaults.outlinedCardColors(
			containerColor = Color.Transparent,
			contentColor = MaterialTheme.colorScheme.onBackground,
		),
		border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.secondary),
		modifier = modifier,
	) {
		Column(
			verticalArrangement = Arrangement.spacedBy(8.dp),
			modifier = Modifier.padding(16.dp)
		) {
			instructions.forEach { instruction ->
				Row {
					Text(
						text = "${instruction.order}.",
						color = MaterialTheme.colorScheme.outline,
						modifier = Modifier.width(32.dp)
					)
					Text(
						text = instruction.label,
						color = MaterialTheme.colorScheme.outline,
						modifier = Modifier.fillMaxWidth()
					)
				}
			}
		}
	}
}

@Composable
@Preview(showSystemUi = true)
fun RecipeInstructionsWidgetPreview() {
	NeuracrTheme {
		RecipeInstructionsWidget(
			listOf(
				InstructionUiModel(1, "Boil some water in a pot"),
				InstructionUiModel(2, "Chop the shallots finely"),
				InstructionUiModel(
					3,
					"Put your salmon in a gratin dish. Season with salt, pepper and some of the shallots. Cover the dish with Cellophane"
				)
			),
			modifier = Modifier.background(Color.White)
		)
	}
}
