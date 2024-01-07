package com.team23.view.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.team23.neuracrsrecipes.model.uistate.HomeUiState
import com.team23.view.HomeScreen
import com.team23.view.sample.uimodel.HomePreviewParameterProvider
import com.team23.view.theme.PopoteTheme

@Composable
@Preview(showSystemUi = true)
private fun HomeScreenPreview(@PreviewParameter(HomePreviewParameterProvider:: class) homeUiState: HomeUiState) {
    PopoteTheme {
        HomeScreen {}
    }
}
