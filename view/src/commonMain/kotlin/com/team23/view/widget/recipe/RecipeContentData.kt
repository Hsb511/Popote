package com.team23.view.widget.recipe

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.uimodel.IngredientsUiModel
import com.team23.neuracrsrecipes.model.uimodel.InstructionsUiModel
import com.team23.neuracrsrecipes.model.uimodel.RecipeUiModel
import com.team23.view.Res
import com.team23.view.ds.button.ButtonLike
import com.team23.view.ds.button.ButtonLocalPhone
import com.team23.view.ds.image.NeuracrImage
import com.team23.view.extension.getCurrentScreenWidth
import com.team23.view.extension.getImageMaxHeight
import com.team23.view.extension.horizontalGutterPadding
import com.team23.view.mapper.FavoriteUiMapper
import com.team23.view.mapper.LocalPhoneUiMapper
import com.team23.view.recipe_ingredients_title
import com.team23.view.recipe_instructions_title
import com.team23.view.recipe_written_by
import org.jetbrains.compose.resources.stringResource

@Composable
fun RecipeContentData(
    recipeUiModel: RecipeUiModel,
    currentServingsAmount: String,
    scrollState: ScrollState,
    heightToBeFaded: MutableState<Float>,
    onValueChanged: (String) -> Unit,
    onAddOneServing: () -> Unit,
    onSubtractOneServing: () -> Unit,
    onTagClicked: (String) -> Unit,
    onFavoriteClick: (RecipeUiModel) -> Unit,
    onLocalPhoneClick: () -> Unit,
    onUpdateLocalRecipe: () -> Unit,
    onDeleteLocalRecipe: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        floatingActionButton = {
            if (recipeUiModel.isLocallySaved) {
                RecipeModifyButton(onUpdateLocalRecipe, onDeleteLocalRecipe)
            }
        }
    ) { padding ->
        val density = LocalDensity.current
        val horizontalPadding = horizontalGutterPadding.value
        val imageHeight = getCurrentScreenWidth() * 3f / 4 - 64.dp
        var yPositionImage by remember { mutableStateOf(541f) }
        var yPositionBottomIngredients by remember { mutableStateOf(1375f) }
        var yPositionTopInstructions by remember { mutableStateOf(1557f) }
        val coeff = (1 - yPositionImage / (4 * horizontalPadding)).coerceIn(0f, 1f)
        val spaceToAddInPx = with(LocalDensity.current) { horizontalPadding.dp.toPx() } * 2
        val headerIngredientFraction = (yPositionBottomIngredients / (yPositionBottomIngredients - yPositionTopInstructions)).coerceIn(0f, 1f)

        Box(modifier = modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(padding)
                    .padding(horizontal = horizontalPadding.dp, vertical = 16.dp)
                    .background(color = MaterialTheme.colorScheme.background)
            ) {
                Spacer(modifier = Modifier.height(64.dp + 8.dp))
                Text(
                    text = recipeUiModel.title,
                    style = MaterialTheme.typography.displaySmall,
                )
                HorizontalDivider(modifier = Modifier
                    .padding(top = 8.dp)
                    .onGloballyPositioned { layoutCoordinates ->
                        heightToBeFaded.value = layoutCoordinates.positionInRoot().y
                    }
                )
                Text(
                    text = with(recipeUiModel) { "$date - ${stringResource(Res.string.recipe_written_by)} $author" },
                    style = MaterialTheme.typography.labelLarge,
                )
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(recipeUiModel.tags) { tag ->
                        ElevatedSuggestionChip(
                            colors = SuggestionChipDefaults.elevatedSuggestionChipColors(
                                containerColor = MaterialTheme.colorScheme.tertiary,
                                labelColor = MaterialTheme.colorScheme.onTertiary,
                            ),
                            onClick = { onTagClicked(tag) },
                            label = {
                                Text(text = tag)
                            }
                        )
                    }
                }
                RecipeImage(
                    recipeUiModel, onFavoriteClick, onLocalPhoneClick,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                        .onGloballyPositioned { layoutCoordinates ->
                            yPositionImage = layoutCoordinates.positionInRoot().y
                        }
                        .graphicsLayer {
                            scaleX = (size.width + spaceToAddInPx * coeff) / size.width
                            scaleY = (size.height + spaceToAddInPx * coeff) / size.height
                            translationY = -with(density) { horizontalPadding.dp.toPx() } * coeff
                        }
                )

                Text(
                    text = stringResource(Res.string.recipe_ingredients_title),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(top = 16.dp, bottom = 4.dp),
                )
                RecipeIngredientsWidget(
                    ingredientsUiModel = IngredientsUiModel.FromRecipeScreen(
                        ingredients = recipeUiModel.ingredients,
                        currentServingsAmount = currentServingsAmount,
                        onValueChanged = onValueChanged,
                        onAddOneServing = onAddOneServing,
                        onSubtractOneServing = onSubtractOneServing,
                    ),
                    modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                        yPositionBottomIngredients = layoutCoordinates.positionInRoot().y +
                            layoutCoordinates.size.height -
                            with(density) { (imageHeight + 24.dp).toPx() }
                    }
                )
                HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
                Text(
                    text = recipeUiModel.description,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = stringResource(Res.string.recipe_instructions_title),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(top = 4.dp, bottom = 8.dp)
                        .onGloballyPositioned { layoutCoordinates ->
                            yPositionTopInstructions = layoutCoordinates.positionInRoot().y -
                                with(density) { (imageHeight + 24.dp).toPx() }
                        }
                )
                RecipeInstructionsWidget(
                    instructions = InstructionsUiModel.FromRecipeScreen(recipeUiModel.instructions),
                )
                Text(
                    text = recipeUiModel.conclusion,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 16.dp),
                )
            }
            Box {
                if (yPositionImage <= 0f) {
                    RecipeImage(
                        recipeUiModel, onFavoriteClick, onLocalPhoneClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f))
                            .padding(horizontal = horizontalPadding.dp)
                            .graphicsLayer {
                                scaleX = (size.width + spaceToAddInPx) / size.width
                                scaleY = (size.height + spaceToAddInPx) / size.height
                                translationY =
                                    -with(density) { horizontalPadding.dp.toPx() } * coeff
                            }
                    )
                }
                if (yPositionBottomIngredients <= 0f) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(imageHeight + 16.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surface.copy(alpha = headerIngredientFraction * 0.5f),
                                shape = RoundedCornerShape(16.dp),
                            )
                    )
                    RecipeIngredientsColumn(
                        ingredientsUiModel = IngredientsUiModel.FromRecipeScreen(
                            ingredients = recipeUiModel.ingredients,
                            currentServingsAmount = currentServingsAmount,
                            onValueChanged = onValueChanged,
                            onAddOneServing = onAddOneServing,
                            onSubtractOneServing = onSubtractOneServing,
                        ),
                        modifier = Modifier
                            .padding(top = 64.dp)
                            .height(imageHeight - 48.dp)
                            .alpha(headerIngredientFraction)
                            .verticalScroll(rememberScrollState())
                    )
                }
            }
        }
    }
}

@Composable
private fun RecipeImage(
    recipeUiModel: RecipeUiModel,
    onFavoriteClick: (RecipeUiModel) -> Unit,
    onLocalPhoneClick: () -> Unit,
    modifier: Modifier,
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = modifier,
    ) {
        Box {
            NeuracrImage(
                neuracrImageProperty = recipeUiModel.image,
                maxImageHeight = getImageMaxHeight(),
            )
            if (recipeUiModel.isLocallySaved) {
                ButtonLocalPhone(
                    localPhone = LocalPhoneUiMapper().toLocalPhoneProperty(onLocalPhoneClick),
                    modifier = Modifier.align(Alignment.TopStart),
                )
            }
            ButtonLike(
                iconProperty = FavoriteUiMapper().toFavoriteIconProperty(recipeUiModel.isFavorite),
                onFavoriteClick = { onFavoriteClick(recipeUiModel) },
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}
