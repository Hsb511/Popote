package com.team23.presentation.recipe.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.R
import com.team23.presentation.add.views.AddTextField
import com.team23.presentation.recipe.models.InstructionUiModel
import com.team23.presentation.recipe.models.InstructionsUiModel

@Composable
fun RecipeInstructionsWidget(
	instructions: InstructionsUiModel,
	modifier: Modifier = Modifier,
	addButtonWidth: Int = 0,
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
			verticalArrangement = Arrangement.spacedBy(if (instructions is InstructionsUiModel.FromRecipeScreen) 8.dp else 2.dp),
			modifier = Modifier.padding(16.dp)
		) {
			instructions.instructions.forEach { instruction ->
				Row(verticalAlignment = Alignment.CenterVertically) {
					Text(
						text = "${instruction.order}.",
						color = MaterialTheme.colorScheme.outline,
						modifier = Modifier.width(32.dp)
					)
					when (instructions) {
						is InstructionsUiModel.FromRecipeScreen -> Text(
							text = instruction.label,
							color = MaterialTheme.colorScheme.outline,
							modifier = Modifier.fillMaxWidth()
						)

						is InstructionsUiModel.FromAddScreen -> {
							AddTextField(
								text = instruction.label,
								onTextChange = { newLabel -> instructions.onUpdateInstruction(instruction.copy(label = newLabel)) },
								style = MaterialTheme.typography.bodyLarge,
								placeholder = stringResource(id = R.string.add_recipe_instruction_placeholder),
								singleLine = false,
								modifier = Modifier.weight(9f)
							)
							IconButton(
								onClick = { instructions.onDeleteInstruction(instruction) },
								colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.secondary),
								modifier = Modifier
									.weight(1f)
									.height(32.dp)
							) {
								Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
							}
						}
					}
				}
			}
			if (instructions is InstructionsUiModel.FromAddScreen) {
				RecipeAddButton(
					onAddClick = instructions.onAddInstruction,
					widgetWidth = addButtonWidth,
					modifier = Modifier.padding(top = 8.dp)
				)
			}
		}
	}
}

@Composable
@Preview(showSystemUi = true)
fun RecipeInstructionsWidgetPreview() {
	NeuracrTheme {
		RecipeInstructionsWidget(
			InstructionsUiModel.FromAddScreen(listOf(
				InstructionUiModel(1, "Boil some water in a pot"),
				InstructionUiModel(2, "Chop the shallots finely"),
				InstructionUiModel(
					3,
					"Put your salmon in a gratin dish. Season with salt, pepper and some of the shallots. Cover the dish with Cellophane"
				)
			), {}, {}) {},
			modifier = Modifier.background(Color.White),
			addButtonWidth = 100,
		)
	}
}
