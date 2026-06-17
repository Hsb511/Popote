package com.team23.view.widget.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.action.CellAction
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.uistate.FavoriteUiState
import com.team23.view.ds.cell.Cell
import com.team23.view.extension.topScreenHeight
import com.team23.view.mapper.RecipeUiMapper

@Composable
fun FavoriteDataScreen(
	state: FavoriteUiState.Data.WithFavorites,
	onRecipeClick: (String) -> Unit,
	onFavoriteClick: (String) -> Unit,
	onDisplayClick: () -> Unit,
	onLocalPhoneClick: () -> Unit,
	onRemoveAllClick: () -> Unit,
	modifier: Modifier = Modifier,
) {
	val displayType = state.displayType
	val summarizedRecipes = state.favorites
	val recipeUiMapper = remember { RecipeUiMapper() }
	val recipeCellProperties = remember {
		summarizedRecipes.map { recipe ->
			recipeUiMapper.toCellProperty(
				recipe = recipe,
				displayType = displayType,
			)
		}
	}

	LazyVerticalStaggeredGrid(
		columns = StaggeredGridCells.Adaptive(if (displayType == DisplayType.SmallCard) 150.dp else 300.dp),
		contentPadding = PaddingValues(if (displayType == DisplayType.BigCard) 32.dp else 16.dp),
		verticalItemSpacing = 16.dp,
		horizontalArrangement = Arrangement.spacedBy(16.dp),
		modifier = modifier.fillMaxSize()
	) {
		item(span = StaggeredGridItemSpan.FullLine) {
			Spacer(modifier = Modifier.topScreenHeight())
		}
		item(span = StaggeredGridItemSpan.FullLine) {
			FavoriteHeader(
				displayType = displayType,
				onDisplayClick = onDisplayClick,
				onRemoveAllClick = onRemoveAllClick,
			)
		}
		items(
			items = recipeCellProperties,
			key = { recipe -> recipe.id },
		) { cellProperty ->
			Cell(
				cellProperty = cellProperty,
				onAction = { action ->
					when (action) {
                        CellAction.FavoriteClick -> onFavoriteClick(cellProperty.id)
                        CellAction.LocalPhoneClick -> onLocalPhoneClick()
                    }
				},
				modifier = Modifier.clickable {
					onRecipeClick(cellProperty.id)
				},
			)
		}
	}
}
