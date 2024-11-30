package com.team23.view.navigation.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.team23.neuracrsrecipes.model.uimodel.AddRecipeUiModel
import com.team23.neuracrsrecipes.model.uimodel.IngredientsUiModel
import com.team23.neuracrsrecipes.model.uimodel.InstructionsUiModel
import com.team23.neuracrsrecipes.viewmodel.AddViewModel
import com.team23.view.LocalHeightToBeFaded
import com.team23.view.LocalScrollState
import com.team23.view.LocalTitle
import com.team23.view.Res
import com.team23.view.add_recipe_author_name
import com.team23.view.add_recipe_conclusion
import com.team23.view.add_recipe_description
import com.team23.view.add_recipe_title
import com.team23.view.extension.horizontalGutterPadding
import com.team23.view.navigation.AppNavigator
import com.team23.view.recipe_ingredients_title
import com.team23.view.recipe_instructions_title
import com.team23.view.recipe_written_by
import com.team23.view.widget.add.AddImageButton
import com.team23.view.widget.add.AddSaveButton
import com.team23.view.widget.add.AddTagSection
import com.team23.view.widget.add.AddTextField
import com.team23.view.widget.recipe.RecipeIngredientsWidget
import com.team23.view.widget.recipe.RecipeInstructionsWidget
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

internal data object AddScreen : Screen {

    @Composable
    override fun Content() {
        val scrollState: ScrollState = LocalScrollState.current
        val heightToBeFaded: MutableState<Float> = LocalHeightToBeFaded.current
        val addViewModel = koinInject<AddViewModel>()
        val appNavigator = koinInject<AppNavigator>()
        val navigator = LocalNavigator.currentOrThrow
        val onRecipeClick = { recipeId: String -> appNavigator.navigateToRecipe(navigator, recipeId) }

        LocalTitle.current.value = null

        Scaffold(
            floatingActionButton = {
                AddSaveButton {
                    addViewModel.onSaveButtonClick(onRecipeClick)
                }
            }
        ) { padding ->
            AddScreen(
                addRecipe = addViewModel.recipe.collectAsState().value,
                allTags = addViewModel.tags.collectAsState().value,
                scrollState = scrollState,
                heightToBeFaded = heightToBeFaded,
                modifier = Modifier.padding(padding),
            )
        }
    }
}

@Composable
fun AddScreen(
    addRecipe: AddRecipeUiModel,
    allTags: List<String>,
    scrollState: ScrollState,
    heightToBeFaded: MutableState<Float>,
    modifier: Modifier = Modifier,
) {
    val addButtonWidth = remember { mutableStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = horizontalGutterPadding, vertical = 16.dp)
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.height(64.dp + 8.dp))
        AddTextField(
            text = addRecipe.recipe.title,
            onTextChange = addRecipe.onTitleChange,
            style = MaterialTheme.typography.displaySmall,
            placeholder = stringResource(Res.string.add_recipe_title),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        HorizontalDivider(modifier = Modifier
            .padding(top = 8.dp)
            .onGloballyPositioned { layoutCoordinates ->
                heightToBeFaded.value = layoutCoordinates.positionInRoot().y
            }
        )
        Row {
            Text(
                text = "${addRecipe.recipe.date} - ${stringResource(Res.string.recipe_written_by)}",
                style = MaterialTheme.typography.labelLarge,
            )
            AddTextField(
                text = addRecipe.recipe.author,
                onTextChange = addRecipe.onAuthorChange,
                style = MaterialTheme.typography.labelLarge,
                placeholder = stringResource(Res.string.add_recipe_author_name),
                singleLine = true,
                modifier = Modifier.padding(start = 8.dp),
            )
        }

        AddTagSection(
            allTags = allTags,
            onAddTag = addRecipe.onAddTag,
            onRemoveTag = addRecipe.onRemoveTag,
        )

        AddImageButton(
            neuracrImageProperty = addRecipe.recipe.image,
            onImageSelected = addRecipe.onAddImage,
            launchSettings = addRecipe.onLaunchSettings,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
        )

        Text(
            text = stringResource(Res.string.recipe_ingredients_title),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(top = 16.dp, bottom = 4.dp),
        )

        RecipeIngredientsWidget(
            ingredientsUiModel = IngredientsUiModel.FromAddScreen(
                ingredients = addRecipe.recipe.ingredients,
                currentServingsAmount = addRecipe.recipe.defaultServingsAmount.toString(),
                onValueChanged = addRecipe.onServingsAmountChange,
                onAddOneServing = addRecipe.onAddOneServing,
                onSubtractOneServing = addRecipe.onSubtractOneServing,
                onAddIngredient = addRecipe.onAddIngredient,
                onDeleteIngredient = addRecipe.onDeleteIngredient,
                onUpdateIngredient = addRecipe.onUpdateIngredient,
            ),
            addButtonWidth = addButtonWidth,
        )
        HorizontalDivider(modifier = Modifier.padding(top = 8.dp))

        AddTextField(
            text = addRecipe.recipe.description,
            onTextChange = addRecipe.onDescriptionChange,
            style = MaterialTheme.typography.bodyMedium,
            placeholder = stringResource(Res.string.add_recipe_description),
            singleLine = false,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 64.dp),
        )

        Text(
            text = stringResource(Res.string.recipe_instructions_title),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(top = 4.dp, bottom = 8.dp),
        )

        RecipeInstructionsWidget(
            instructions = InstructionsUiModel.FromAddScreen(
                instructions = addRecipe.recipe.instructions,
                onAddInstruction = addRecipe.onAddInstruction,
                onDeleteInstruction = addRecipe.onDeleteInstruction,
                onUpdateInstruction = addRecipe.onUpdateInstruction,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            addButtonWidth = addButtonWidth.value,
        )

        AddTextField(
            text = addRecipe.recipe.conclusion,
            onTextChange = addRecipe.onConclusionChange,
            style = MaterialTheme.typography.bodyMedium,
            placeholder = stringResource(Res.string.add_recipe_conclusion),
            singleLine = false,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 64.dp),
        )
    }
}
