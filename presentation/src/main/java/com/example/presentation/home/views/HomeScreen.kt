package com.example.presentation.home.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.design_system.R
import com.example.design_system.flags.NeuracrFlagProperty
import com.example.design_system.image.NeuracrImageProperty
import com.example.design_system.theming.NeuracrTheme
import com.example.presentation.home.models.HomeRecipeUiModel
import com.example.presentation.home.models.HomeUiState
import com.example.presentation.home.models.HomeUiState.*
import com.example.presentation.home.viewmodels.HomeViewModel

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
		is Error -> {}
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
