package com.example.presentation.home.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.design_system.flags.NeuracrFlagProperty
import com.example.design_system.image.NeuracrImageProperty
import com.example.design_system.theming.NeuracrTheme
import com.example.presentation.home.models.HomeRecipeUiModel
import com.example.presentation.home.models.HomeUiState
import com.example.presentation.home.models.HomeUiState.*
import com.example.presentation.home.viewmodels.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreen(homeUiState = homeViewModel.uiState.collectAsState().value, modifier = modifier)
}

@Composable
private fun HomeScreen(
    homeUiState: HomeUiState,
    modifier: Modifier = Modifier
) {
    when (homeUiState) {
        is Loading -> HomeContentLoading(modifier)
        is Error -> {}
        is Data -> HomeContentData(homeUiState.recipes, modifier)
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
                            imageRes = com.example.design_system.R.drawable.bretzel
                        ),
                        flagProperty = NeuracrFlagProperty.FRENCH,
                    )
                }
            )
        )
    }
}
