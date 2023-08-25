package com.team23.presentation.add

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team23.design_system.image.NeuracrImageProperty
import com.team23.domain.usecases.CreateNewRecipeUseCase
import com.team23.domain.usecases.GetAndSortAllTagsUseCase
import com.team23.domain.usecases.LoadTemporaryRecipeUseCase
import com.team23.domain.usecases.SaveRecipeUseCase
import com.team23.domain.usecases.UpdateTempRecipeUseCase
import com.team23.presentation.add.models.AddRecipeUiModel
import com.team23.presentation.common.handlers.SnackbarHandler
import com.team23.presentation.recipe.mappers.RecipeMapper
import com.team23.presentation.recipe.models.IngredientUiModel
import com.team23.presentation.recipe.models.InstructionUiModel
import com.team23.presentation.recipe.models.RecipeUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
	updateTempRecipeUseCase: UpdateTempRecipeUseCase,
	private val createNewRecipeUseCase: CreateNewRecipeUseCase,
	private val recipeMapper: RecipeMapper,
	private val getAndSortAllTagsUseCase: GetAndSortAllTagsUseCase,
	private val loadTemporaryRecipeUseCase: LoadTemporaryRecipeUseCase,
	private val saveRecipeUseCase: SaveRecipeUseCase,
) : ViewModel() {
	private val _recipe = MutableStateFlow(createNewRecipe())
	val recipe: StateFlow<AddRecipeUiModel> = _recipe
		.onEach { recipeUiModel ->
			viewModelScope.launch(Dispatchers.IO) {
				updateTempRecipeUseCase.invoke(recipeMapper.toRecipeDomainModel(recipeUiModel))
			}
		}
		.map(::createAddRecipe)
		.stateIn(viewModelScope, SharingStarted.Eagerly, createAddRecipe(_recipe.value))

	private val _tags = MutableStateFlow<List<String>>(emptyList())
	val tags: StateFlow<List<String>> = _tags

	private fun createAddRecipe(recipe: RecipeUiModel) = AddRecipeUiModel(
		recipe = recipe,
		onTitleChange = { newTitle -> onTitleChange(newTitle) },
		onAuthorChange = { newAuthor -> onAuthorChange(newAuthor) },
		onAddTag = { newTag -> onAddTag(newTag) },
		onRemoveTag = { tag -> onRemoveTag(tag) },
		onAddImage = { image -> onAddImage(image) },
		onAddIngredient = { onAddIngredient() },
		onDeleteIngredient = { index -> onDeleteIngredient(index) },
		onUpdateIngredient = { ingredient, index -> onUpdateIngredient(ingredient, index) },
		onServingsAmountChange = { newServingsAmount -> onServingsAmountChange(newServingsAmount) },
		onAddOneServing = { onAddOneServing() },
		onSubtractOneServing = { onSubtractOneServing() },
		onDescriptionChange = { newDescription -> onDescriptionChange(newDescription) },
		onAddInstruction = { onAddInstruction() },
		onDeleteInstruction = { instruction -> onDeleteInstruction(instruction) },
		onUpdateInstruction = { instruction -> onUpdateInstruction(instruction) },
		onConclusionChange = { newConclusion -> onConclusionChange(newConclusion) },
	)

	init {
		viewModelScope.launch(Dispatchers.IO) {
			_tags.value = getAndSortAllTagsUseCase.invoke()
			loadTemporaryRecipeUseCase.invoke()?.let { temporaryRecipe ->
				_recipe.value = recipeMapper.toRecipeUiModel(temporaryRecipe)
			}
		}
	}

	fun onSaveButtonClick(
		onRecipeClick: (String) -> Unit,
		snackbarHostState: SnackbarHostState,
		context: Context,
	) {
		val recipeTitle = _recipe.value.title
		viewModelScope.launch(Dispatchers.IO) {
			val savedRecipeId = saveRecipeUseCase.invoke(recipeMapper.toRecipeDomainModel(_recipe.value))
			val result = SnackbarHandler(snackbarHostState, context).showRecipeHasBeenSaved(recipeTitle)
			if (result == SnackbarResult.ActionPerformed) {
				withContext(Dispatchers.Main) {
					onRecipeClick(savedRecipeId)
				}
			}
		}
		_recipe.value = createNewRecipe()
	}

	private fun createNewRecipe() = recipeMapper.toRecipeUiModel(createNewRecipeUseCase.invoke())

	private fun onTitleChange(newTitle: String) {
		_recipe.value = _recipe.value.copy(title = newTitle)
	}

	private fun onAuthorChange(newAuthor: String) {
		_recipe.value = _recipe.value.copy(author = newAuthor)
	}

	private fun onAddTag(newTag: String) {
		_recipe.value = _recipe.value.copy(tags = _recipe.value.tags + newTag)
		val tags = _tags.value.toMutableList()
		tags.remove(newTag)
		_tags.value = tags
	}

	private fun onRemoveTag(tag: String) {
		val currentTags = _recipe.value.tags.toMutableList()
		currentTags.remove(tag)
		_recipe.value = _recipe.value.copy(tags = currentTags)
		_tags.value = _tags.value + tag
	}

	private fun onAddImage(image: NeuracrImageProperty) {
		_recipe.value = _recipe.value.copy(image = image)
	}

	private fun onAddIngredient() {
		val newIngredients = _recipe.value.ingredients + IngredientUiModel("", "", "")
		_recipe.value = _recipe.value.copy(ingredients = newIngredients)
	}

	private fun onDeleteIngredient(index: Int) {
		val ingredients = _recipe.value.ingredients.toMutableList()
		ingredients.removeAt(index)
		_recipe.value = _recipe.value.copy(ingredients = ingredients)
	}

	private fun onUpdateIngredient(ingredient: IngredientUiModel, index: Int) {
		val ingredients = _recipe.value.ingredients.toMutableList()
		ingredients.removeAt(index)
		ingredients.add(index, ingredient)
		_recipe.value = _recipe.value.copy(ingredients = ingredients)
	}

	private fun onServingsAmountChange(newAmount: String) {
		val newServingsAmount = newAmount
			.toIntOrNull()
			?.coerceIn(SERVINGS_AMOUNT_MIN, SERVINGS_AMOUNT_MAX)
			?: SERVINGS_AMOUNT_MIN
		_recipe.value = _recipe.value.copy(defaultServingsAmount = newServingsAmount)
	}

	private fun onAddOneServing() {
		if (_recipe.value.defaultServingsAmount < SERVINGS_AMOUNT_MAX) {
			_recipe.value = _recipe.value.copy(defaultServingsAmount = _recipe.value.defaultServingsAmount + 1)
		}
	}

	private fun onSubtractOneServing() {
		if (_recipe.value.defaultServingsAmount > SERVINGS_AMOUNT_MIN) {
			_recipe.value = _recipe.value.copy(defaultServingsAmount = _recipe.value.defaultServingsAmount - 1)
		}
	}

	private fun onDescriptionChange(newDescription: String) {
		_recipe.value = _recipe.value.copy(description = newDescription)
	}

	private fun onAddInstruction() {
		val instructions = _recipe.value.instructions
		val newInstruction = InstructionUiModel(order = instructions.size + 1, label = "")
		_recipe.value = _recipe.value.copy(instructions = instructions + newInstruction)
	}

	private fun onDeleteInstruction(instruction: InstructionUiModel) {
		val instructions = _recipe.value.instructions.toMutableList()
		instructions.remove(instruction)
		_recipe.value = _recipe.value.copy(instructions = instructions)
	}

	private fun onUpdateInstruction(newInstruction: InstructionUiModel) {
		val instructions = _recipe.value.instructions.toMutableList()
		val index = instructions.indexOfFirst { instruction -> newInstruction.order == instruction.order }
		instructions.removeAt(index)
		instructions.add(index, newInstruction)
		_recipe.value = _recipe.value.copy(instructions = instructions)
	}

	private fun onConclusionChange(newConclusion: String) {
		_recipe.value = _recipe.value.copy(conclusion = newConclusion)
	}
}

private const val SERVINGS_AMOUNT_MIN = 1
private const val SERVINGS_AMOUNT_MAX = 999
