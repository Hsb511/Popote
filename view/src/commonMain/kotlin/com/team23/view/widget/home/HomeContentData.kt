package com.team23.view.widget.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel
import com.team23.view.ds.cell.Cell
import com.team23.view.extension.horizontalGutterPadding
import com.team23.view.extension.stringResource
import com.team23.view.mapper.RecipeUiMapper

@Composable
fun HomeContentData(
	summarizedRecipeUiModels: List<SummarizedRecipeUiModel>,
	homeRecipeClick: (String) -> Unit,
	onFavoriteClick: (SummarizedRecipeUiModel) -> Unit,
	onLocalPhoneClick: () -> Unit,
) {
	val recipeUiMapper = RecipeUiMapper()

	LazyVerticalStaggeredGrid(
		columns = StaggeredGridCells.Adaptive(300.dp),
		contentPadding = PaddingValues(horizontalGutterPadding),
		verticalItemSpacing = 16.dp,
		horizontalArrangement = Arrangement.spacedBy(16.dp),
		modifier = Modifier.fillMaxSize()
	) {
		item(span = StaggeredGridItemSpan.FullLine) {
			Spacer(modifier = Modifier.height(48.dp))
		}
		item(span = StaggeredGridItemSpan.FullLine) {
			Text(
				text = stringResource(id = "home_title"),
				style = MaterialTheme.typography.displaySmall,
				color = MaterialTheme.colorScheme.onBackground,
			)
		}
		items(summarizedRecipeUiModels) { summarizedRecipeUiModel ->
			Cell(
				cellProperty = recipeUiMapper.toCellProperty(
					recipe = summarizedRecipeUiModel,
					displayType = DisplayType.BigCard,
					onFavoriteClick = { onFavoriteClick(summarizedRecipeUiModel) },
					onLocalPhoneClick = onLocalPhoneClick,
				),
				modifier = Modifier
					.fillMaxWidth()
					.clickable {
						homeRecipeClick(summarizedRecipeUiModel.id)
					}
			)
		}
	}
}
