package com.team23.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.team23.design_system.R
import com.team23.design_system.error.NeuracrError
import com.team23.design_system.flags.NeuracrFlagProperty
import com.team23.design_system.image.NeuracrImageProperty
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.home.models.HomeRecipeUiModel
import com.team23.presentation.home.models.HomeUiState
import com.team23.presentation.home.models.HomeUiState.*
import com.team23.presentation.home.views.HomeContentData
import com.team23.presentation.home.views.HomeContentLoading

@Composable
fun HomeScreen(
	homeRecipeClick: (HomeRecipeUiModel) -> Unit,
	modifier: Modifier = Modifier,
	homeViewModel: HomeViewModel = hiltViewModel()
) {
	HomeScreen(
		homeUiState = homeViewModel.uiState.collectAsState().value,
		homeRecipeClick = homeRecipeClick,
		modifier = modifier
	)
}

@Composable
private fun HomeScreen(
	homeUiState: HomeUiState,
	homeRecipeClick: (HomeRecipeUiModel) -> Unit,
	modifier: Modifier = Modifier
) {
	when (homeUiState) {
		is Loading -> HomeContentLoading(modifier)
		is Error -> NeuracrError(homeUiState.message, modifier)
		is Data -> HomeContentData(
			homeRecipeUiModels = homeUiState.recipes,
			homeRecipeClick = homeRecipeClick,
			modifier = modifier,
		)
	}
}

@Composable
@Preview(showSystemUi = true)
private fun HomeScreenPreview() {
	NeuracrTheme {
		HomeScreen(
			homeUiState = Data(
				recipes = List(6) {
					HomeRecipeUiModel(
						id = "",
						title = "bretzels",
						imageProperty = NeuracrImageProperty.Resource(
							contentDescription = null,
							imageRes = R.drawable.bretzel
						),
						flagProperty = NeuracrFlagProperty.FRENCH,
					)
				}
			), {}
		)
	}
}
